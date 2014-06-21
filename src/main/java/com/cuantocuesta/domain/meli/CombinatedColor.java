package com.cuantocuesta.domain.meli;

import com.cuantocuesta.domain.NamedColor;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.List;

public class CombinatedColor {
  private final String primary;
  private final String secondaryColor;

  public CombinatedColor(String primary, String secondaryColor) {
    this.primary = primary;
    this.secondaryColor = secondaryColor;
  }

  public String getPrimary() {
    return primary;
  }

  public String getRgbFrom(List<NamedColor> availableColors) {
    return Iterables.find(availableColors, new Predicate<NamedColor>() {
      @Override
      public boolean apply(NamedColor input) {
        return input.getName().equals(getPrimary());
      }
    }).getRgb();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CombinatedColor that = (CombinatedColor) o;

    if (!primary.equals(that.primary)) return false;
    if (secondaryColor != null ? !secondaryColor.equals(that.secondaryColor) : that.secondaryColor != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = primary.hashCode();
    result = 31 * result + (secondaryColor != null ? secondaryColor.hashCode() : 0);
    return result;
  }
}
