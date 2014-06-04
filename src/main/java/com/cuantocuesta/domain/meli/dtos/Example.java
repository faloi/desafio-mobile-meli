
package com.cuantocuesta.domain.meli.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
  public String query;

  public List<Listing> getResults() {
    return results;
  }

  public List<Listing> results = new ArrayList<Listing>();
}
