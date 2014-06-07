
package com.cuantocuesta.domain.meli.dtos;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
  public String query;

  public Example(Iterable<Listing> listings) {
    this.results = Lists.newArrayList(listings);
  }

  public List<Listing> getResults() {
    return results;
  }

  public List<Listing> results = new ArrayList<Listing>();
}
