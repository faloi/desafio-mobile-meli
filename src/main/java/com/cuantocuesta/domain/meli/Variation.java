package com.cuantocuesta.domain.meli;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Variation {
  public List<String> pictureIds;
  public List<AttributeCombination> attributeCombinations;

  public Variation() {
    this(new ArrayList<AttributeCombination>());
  }

  public Variation(List<AttributeCombination> attributeCombinations) {
    this(attributeCombinations, new ArrayList<String>());
  }

  public Variation(List<AttributeCombination> attributeCombinations, List<String> pictureIds) {
    this.attributeCombinations = attributeCombinations;
    this.pictureIds = pictureIds;
  }

  public CombinatedColor getCombinatedColor(Listing listing) {
    CombinatedColor combinatedColor = new CombinatedColor(this.getPrimaryColor(), this.getSecondaryColor());
    combinatedColor.setPictures(getPicturesFrom(listing));

    return combinatedColor;
  }

  private Set<String> getPicturesFrom(final Listing listing) {
    return ImmutableSet.copyOf(Iterables.transform(getPictureIds(), new Function<String, String>() {
      @Override
      public String apply(String input) {
        return listing.getPictureById(input);
      }
    }));
  }

  private String getPrimaryColor() {
    return getColor("pri");
  }

  private String getSecondaryColor() {
    return getColor("sec");
  }

  private String getColor(final String nameToSearch) {
    return Iterables.tryFind(getAttributeCombinations(), new Predicate<AttributeCombination>() {
      @Override
      public boolean apply(AttributeCombination input) {
        String normalizedName = input.getNormalizedName();
        return normalizedName.contains("co") && normalizedName.contains(nameToSearch);
      }
    }).or(new AttributeCombination("", null)).getValueName();
  }

  public List<AttributeCombination> getAttributeCombinations() {
    return attributeCombinations;
  }

  public String getSize() {
    return Iterables.find(getAttributeCombinations(), new Predicate<AttributeCombination>() {
      @Override
      public boolean apply(AttributeCombination input) {
        return input.getNormalizedName().startsWith("ta");
      }
    }).getValueName();
  }

  public List<String> getPictureIds() {
    return pictureIds;
  }
}
