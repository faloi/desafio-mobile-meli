package com.cuantocuesta.domain.meli.adapters;

import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.dtos.Result;
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
    Result result = new Result();
    result.setCategory_id("MLA8256");
    result.setPrice(12.5);

    assertEquals(new Listing(12.5, "MLA8256"), adapter.getValueFor(result));
  }
}
