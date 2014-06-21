package com.cuantocuesta.domain.meli;

import com.cuantocuesta.domain.NamedColor;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.List;
import java.util.Set;

public class CombinatedColor {
  private final String primary;
  private final String secondary;
  private Set<String> pictures;

  public CombinatedColor(String primary, String secondary) {
    this.primary = primary;
    this.secondary = secondary;
  }

  public CombinatedColor(String primary) {
    this(primary, null);
  }

  public String getPrimary() {
    return primary;
  }

  public CombinatedColor getRgbFrom(List<NamedColor> availableColors) {
    CombinatedColor value = new CombinatedColor(
      getRgbFor(availableColors, getPrimary()),
      getRgbFor(availableColors, getSecondary())
    );

    value.setPictures(this.getPictures());

    return value;
  }

  private String getRgbFor(List<NamedColor> availableColors, final String color) {
    return Iterables.tryFind(availableColors, new Predicate<NamedColor>() {
      @Override
      public boolean apply(NamedColor input) {
        return input.getName().equals(color);
      }
    }).or(new NamedColor("", null)).getRgb();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CombinatedColor that = (CombinatedColor) o;

    if (!primary.equals(that.primary)) return false;
    if (secondary != null ? !secondary.equals(that.secondary) : that.secondary != null)
      return false;

    return true;
  }

  @Override
  public String toString() {
    return primary + " - " + secondary;
  }

  @Override
  public int hashCode() {
    int result = primary.hashCode();
    result = 31 * result + (secondary != null ? secondary.hashCode() : 0);
    return result;
  }

  public String getSecondary() {
    return secondary;
  }

  public Set<String> getPictures() {
    return pictures;
  }

  public void setPictures(Set<String> pictures) {
    this.pictures = pictures;
  }
}
