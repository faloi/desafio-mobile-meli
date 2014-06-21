package com.cuantocuesta.domain.meli;

public class AttributeCombination {
  public String name;
  public String valueName;

  public AttributeCombination() {
  }

  public AttributeCombination(String name, String valueName) {
    this.name = name;
    this.valueName = valueName;
  }

  public String getValueName() {
    return valueName;
  }

  String getNormalizedName() {
    return name.toLowerCase();
  }
}
