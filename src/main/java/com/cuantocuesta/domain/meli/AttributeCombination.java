package com.cuantocuesta.domain.meli;

import org.apache.commons.lang3.StringUtils;

public class AttributeCombination implements Comparable<AttributeCombination> {
  public String name;
  public String valueId;
  public String valueName;

  public AttributeCombination() {
  }

  public AttributeCombination(String name, String valueName) {
    this(name, "", valueName);
  }

  public AttributeCombination(String name, String valueId, String valueName) {
    this.name = name;
    this.valueId = valueId;
    this.valueName = valueName;
  }

  public String getValueName() {
    return valueName;
  }

  String getNormalizedName() {
    return name.toLowerCase();
  }

  @Override
  public int compareTo(AttributeCombination another) {
    if (StringUtils.isNumeric(this.getValueName()))
      return this.getValueName().compareTo(another.getValueName());
    else
      return this.getValueId().compareTo(another.getValueId());
  }

  public String getValueId() {
    return valueId;
  }
}
