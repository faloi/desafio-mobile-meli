package com.cuantocuesta.domain.meli;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ListingTest {

  @Test
  public void Can_tell_if_it_has_free_shipping() {
    Listing listing = new Listing();
    listing.shipping = new Shipping(true);

    assertTrue(listing.hasFreeShipping());
  }

  @Test
  public void Can_tell_if_it_doesnt_have_free_shipping() {
    Listing listing = new Listing();
    listing.shipping = new Shipping(false);

    assertFalse(listing.hasFreeShipping());
  }

}
