package com.cuantocuesta.domain.meli;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

public class Variation {
  public List<AttributeCombination> attributeCombinations;

  public Variation() {
    this.attributeCombinations = new ArrayList<AttributeCombination>();
  }

  public Variation(List<AttributeCombination> attributeCombinations) {
    this.attributeCombinations = attributeCombinations;
  }

  public CombinatedColor getCombinatedColor() {
    return new CombinatedColor(this.getPrimaryColor(), this.getSecondaryColor());
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
        String normalizedName = input.name.toLowerCase();
        return normalizedName.contains("co") && normalizedName.contains(nameToSearch);
      }
    }).or(new AttributeCombination("", null)).valueName;
  }

  public List<AttributeCombination> getAttributeCombinations() {
    return attributeCombinations;
  }
}
