package com.cuantocuesta.domain;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.observers.LikingLearningObserver;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.util.*;

public class ListingsStream {
  public static final int LIMIT = 20;
  public static final int DISLIKE_FACTOR = 5;

  public static ListingsStream getInstance() {
    return instance;
  }

  public static ListingsStream create(Meli service, String site, List<String> relevantCategories, Iterable<LikingLearningObserver> observers) {
    instance = new ListingsStream(service, site, relevantCategories, observers);
    return getInstance();
  }

  private static ListingsStream instance;

  private Meli service;
  private String site;
  private Iterable<LikingLearningObserver> observers;
  private List<Listing> likedListings = new ArrayList<Listing>();
  private List<Listing> dislikedListings = new ArrayList<Listing>();
  private Map<String, Integer> offsets = new HashMap<String, Integer>();
  private Map<String, Integer> dislikedCategories = new HashMap<String, Integer>();

  public ListingsStream(Meli service, String site, List<String> relevantCategories) {
    this(service, site, relevantCategories, new ArrayList<LikingLearningObserver>());
  }

  public ListingsStream(Meli service, String site, List<String> relevantCategories, Iterable<LikingLearningObserver> observers) {
    this.service = service;
    this.site = site;
    this.observers = observers;

    this.offsets = Maps.newHashMap(
      Maps.asMap(new HashSet<String>(relevantCategories), new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
          return 0;
        }
      })
    );
  }

  public List<Listing> getMoreListings() {
    Iterable<List<Listing>> examples = Iterables.transform(getRelevantCategories(), new Function<String, List<Listing>>() {
      @Override
      public List<Listing> apply(String category) {
        return getListingsByCategory(category);
      }
    });

    Iterable<Listing> listings = Iterables.filter(Iterables.concat(examples), new Predicate<Listing>() {
      @Override
      public boolean apply(Listing input) {
        return !isCategoryDisliked(input.getCategoryId());
      }
    });

    if (listings.iterator().hasNext())
      return randomize(fetchAdditionalData(listings));
    else
      return this.getMoreListings();
  }

  private boolean isCategoryDisliked(String categoryId) {
    return this.getDislikedCategoryCount(categoryId) >= DISLIKE_FACTOR;
  }

  private int getDislikedCategoryCount(String categoryId) {
    Integer value = this.dislikedCategories.get(categoryId);
    return value == null ? 0 : value;
  }

  private List<Listing> fetchAdditionalData(Iterable<Listing> listings) {
    String ids = Joiner.on(",").join(Iterables.transform(listings, new Function<Listing, Object>() {
      @Override
      public String apply(Listing listing) {
        return listing.getId();
      }
    }));

    return service.getListingsDetails(ids);
  }

  public void registerLike(Listing listing) {
    this.likedListings.add(listing);
  }

  public void registerDislike(Listing listing) {
    this.dislikedListings.add(listing);
    this.addDislikedCategory(listing);
  }

  private void addDislikedCategory(Listing listing) {
    String categoryId = listing.getCategoryId();

    Integer actualCount = this.dislikedCategories.get(categoryId);

    if (actualCount == null)
      this.dislikedCategories.put(categoryId, 0);
    else {
      this.dislikedCategories.put(categoryId, actualCount + 1);
      if (isCategoryDisliked(categoryId))
        for (LikingLearningObserver o : getObservers()) o.notifyListingsLikeThisWillBeExcluded(listing);
    }
  }

  private List<Listing> randomize(Iterable<Listing> listings) {
    return Ordering.arbitrary().sortedCopy(listings);
  }

  private List<Listing> getListingsByCategory(String category) {
    int offset = getOffsetFor(category);

    List<Listing> results = service.searchByCategory(site, category, offset, LIMIT).getResults();
    increaseOffset(category);

    return results;
  }

  private void increaseOffset(String category) {
    offsets.put(category, offsets.get(category) + LIMIT);
  }

  private int getOffsetFor(String category) {
    return offsets.get(category);
  }

  private Set<String> getRelevantCategories() {
    return this.offsets.keySet();
  }

  public Iterable<LikingLearningObserver> getObservers() {
    return observers;
  }

  public List<Listing> getLikedListings() {
    return likedListings;
  }
}
