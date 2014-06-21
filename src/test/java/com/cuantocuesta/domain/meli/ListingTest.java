package com.cuantocuesta.domain.meli;

import com.cuantocuesta.domain.NamedColor;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

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

  @Test
  public void Can_calculate_the_colors_of_its_variations_when_there_are_only_primary_colors() {
    listing.addVariations(
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Preto"),
        new AttributeCombination("Tamanho", "35")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Vermelho"),
        new AttributeCombination("Tamanho", "35")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Preto"),
        new AttributeCombination("Tamanho", "36")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Vermelho"),
        new AttributeCombination("Tamanho", "36")
      ))
    );

    assertEquals(
      Arrays.asList("#FF0000", "#000000"),
      listing.getColorsRgbs(Arrays.asList(
        new NamedColor("Preto", "#000000"), new NamedColor("Vermelho", "#FF0000"), new NamedColor("Rosa", "#F4CCCC")
      ))
    );
  }
}
