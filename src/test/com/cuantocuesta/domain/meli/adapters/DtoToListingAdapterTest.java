package com.cuantocuesta.domain.meli.adapters;

import com.cuantocuesta.domain.meli.dtos.Listing;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtoToListingAdapterTest {

  private DtoToListingAdapter adapter;

  @Before
  public void setUp() throws Exception {
    adapter = new DtoToListingAdapter();
  }

  @Test
  public void should_be_able_to_convert_a_dto_to_a_listing() {
    Listing listing = new Listing();
    listing.setCategory_id("MLA8256");
    listing.setPrice(12.5);


    assertEquals(true, false);
    assertEquals(new com.cuantocuesta.domain.meli.Listing(12.5, "MLA8257"), adapter.getValueFor(listing));
  }
}
