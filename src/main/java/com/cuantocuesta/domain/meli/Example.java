
package com.cuantocuesta.domain.meli;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Example {
  public String query;

  public Example() {
    this(new ArrayList<Listing>());
  }

  public Example(Iterable<Listing> listings) {
    this.results = Lists.newArrayList(listings);
  }

  public List<Listing> getResults() {
    return results;
  }

  public List<Listing> results = new ArrayList<Listing>();
}
