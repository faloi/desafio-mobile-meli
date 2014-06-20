package com.cuantocuesta.domain.meli;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListingTest {

  private Listing listing;

  @Before
  public void setUp() throws Exception {
    listing = new Listing();
  }

  @Test
  public void Can_tell_if_it_has_free_shipping() {
    listing.shipping = new Shipping(true);
    assertTrue(listing.hasFreeShipping());
  }

  @Test
  public void Can_tell_if_it_doesnt_have_free_shipping() {
    listing.shipping = new Shipping(false);
    assertFalse(listing.hasFreeShipping());
  }

  @Test
  public void Can_tell_if_it_is_an_item_of_an_official_store() {
    listing.officialStoreId = 62l;
    assertTrue(listing.isFromOfficialStore());
  }

  @Test
  public void Can_tell_if_its_not_an_item_of_an_official_store() {
    listing.officialStoreId = null;
    assertFalse(listing.isFromOfficialStore());
  }

}
