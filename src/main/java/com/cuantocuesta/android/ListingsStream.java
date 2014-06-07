package com.cuantocuesta.android;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

import java.util.List;

public class ListingsStream {
  private static final int LIMIT = 10;

  private Meli service;
  private String site;
  private List<String> relevantCategories;

  public ListingsStream(Meli service, String site, List<String> relevantCategories) {
    this.service = service;
    this.site = site;
    this.relevantCategories = relevantCategories;
  }

  public Example fetchInitialListings() {
    Iterable<List<Listing>> examples = Iterables.transform(relevantCategories, new Function<String, List<Listing>>() {
      @Override
      public List<Listing> apply(String category) {
        return getListingsByCategory(category);
      }
    });

    Iterable<Listing> listings = Iterables.concat(examples);

    return new Example(Ordering.arbitrary().sortedCopy(listings));
  }

  private List<Listing> getListingsByCategory(String category) {
    return service.searchByCategory(site, category, 0, LIMIT).getResults();
  }
}
