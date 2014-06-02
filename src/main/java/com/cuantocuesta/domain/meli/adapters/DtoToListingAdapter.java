package com.cuantocuesta.domain.meli.adapters;

import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.dtos.Result;

public class DtoToListingAdapter {
  public Listing getValueFor(Result result) {
    return new Listing(result.getPrice(), result.getCategory_id());
  }
}
