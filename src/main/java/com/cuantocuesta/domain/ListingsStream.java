package com.cuantocuesta.domain;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.Listing;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.util.*;

public class ListingsStream {
  public static final int LIMIT = 1;

  private Meli service;
  private String site;

  private List<Listing> likedListings = new ArrayList<Listing>();
  private List<Listing> dislikedListings = new ArrayList<Listing>();
  private Map<String, Integer> offsets = new HashMap<String, Integer>();

  public ListingsStream(Meli service, String site, List<String> relevantCategories) {
    this.service = service;
    this.site = site;

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

    Iterable<Listing> listings = Iterables.concat(examples);

    return randomize(fetchAdditionalData(listings));
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
}
