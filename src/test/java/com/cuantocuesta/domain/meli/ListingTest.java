package com.cuantocuesta.domain.meli;

import com.cuantocuesta.domain.NamedColor;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ListingTest {

  private Listing listing;
  private List<NamedColor> availableColors;

  @Before
  public void setUp() throws Exception {
    listing = new Listing();
    availableColors = Arrays.asList(
      new NamedColor("Preto", "#000000"), new NamedColor("Vermelho", "#FF0000"), new NamedColor("Rosa", "#F4CCCC")
    );
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
      ImmutableSet.of(new CombinatedColor("#FF0000"), new CombinatedColor("#000000")),
      listing.getColors(availableColors)
    );
  }

  @Test
  public void Can_calculate_the_colors_of_its_variations_when_there_are_primary_and_secondary_colors() {
    listing.addVariations(
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Preto"),
        new AttributeCombination("Cor Secundária", "Vermelho"),
        new AttributeCombination("Tamanho", "35")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Preto"),
        new AttributeCombination("Cor Secundária", "Vermelho"),
        new AttributeCombination("Tamanho", "36")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Preto"),
        new AttributeCombination("Tamanho", "36")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Cor principal", "Vermelho"),
        new AttributeCombination("Cor Secundária", "Rosa"),
        new AttributeCombination("Tamanho", "35")
      ))
    );

    assertEquals(
      ImmutableSet.of(new CombinatedColor("#FF0000", "#F4CCCC"), new CombinatedColor("#000000"), new CombinatedColor("#000000", "#FF0000")),
      listing.getColors(availableColors)
    );
  }

  @Test
  public void Can_calculate_the_sizes_of_its_variations() {
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
      ImmutableSet.of("35", "36"),
      listing.getSizes()
    );
  }

  @Test
  public void Fixed_sizes_should_be_ordered_by_their_ids() {
    listing.addVariations(
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "71993", "PP")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "71996", "G")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "71995", "M")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "71994", "P")
      ))
    );

    Iterator<String> sizesIterator = listing.getSizes().iterator();
    assertEquals("PP", sizesIterator.next());
    assertEquals("P", sizesIterator.next());
    assertEquals("M", sizesIterator.next());
    assertEquals("G", sizesIterator.next());
  }

  @Test
  public void Numerical_sizes_should_be_ordered_by_their_natural_order() {
    listing.addVariations(
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "42")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "38")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "40")
      )),
      new Variation(Arrays.asList(
        new AttributeCombination("Tamanho", "36")
      ))
    );

    Iterator<String> sizesIterator = listing.getSizes().iterator();
    assertEquals("36", sizesIterator.next());
    assertEquals("38", sizesIterator.next());
    assertEquals("40", sizesIterator.next());
    assertEquals("42", sizesIterator.next());
  }


  @Test
  public void Can_calculate_the_pictures_for_each_combinated_color() {
    listing.addPictures(
      new Picture("1", "1.jpeg"),
      new Picture("2", "2.jpeg"),
      new Picture("3", "3.jpeg"),
      new Picture("4", "4.jpeg")
    );

    listing.addVariations(
      new Variation(
        Arrays.asList(new AttributeCombination("Cor principal", "Preto")),
        Arrays.asList("1", "2")
      ),
      new Variation(
        Arrays.asList(new AttributeCombination("Cor principal", "Vermelho")),
        Arrays.asList("3", "4")
      )
    );

    Set<CombinatedColor> colors = listing.getColors(availableColors);

    assertEquals(ImmutableSet.of("1.jpeg", "2.jpeg"), findColorPictures(colors, new CombinatedColor("#000000")));
    assertEquals(ImmutableSet.of("3.jpeg", "4.jpeg"), findColorPictures(colors, new CombinatedColor("#FF0000")));
  }

  private Set<String> findColorPictures(Set<CombinatedColor> colors, final CombinatedColor color) {
    return Iterables.find(colors, new Predicate<CombinatedColor>() {
      @Override
      public boolean apply(CombinatedColor input) {
        return input.equals(color);
      }
    }).getPictures();
  }
}
