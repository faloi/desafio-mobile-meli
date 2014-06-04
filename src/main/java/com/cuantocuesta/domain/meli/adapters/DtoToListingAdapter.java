package com.cuantocuesta.domain.meli.adapters;

import com.cuantocuesta.domain.meli.dtos.Listing;

public class DtoToListingAdapter {
  public com.cuantocuesta.domain.meli.Listing getValueFor(Listing listing) {
    return new com.cuantocuesta.domain.meli.Listing(listing.getPrice(), listing.getCategory_id());
  }
}
