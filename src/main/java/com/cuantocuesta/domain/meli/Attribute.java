package com.cuantocuesta.domain.meli;

import java.util.Arrays;
import java.util.List;

public class Attribute {
  public String type;
  public List<AttributeValue> values;

  public Attribute(String type) {
    this.type = type;
  }

  public Attribute(String type, AttributeValue... values) {
    this(type);
    this.values = Arrays.asList(values);
  }

  public boolean isColor() {
    return this.getType() == "color";
  }

  public String getType() {
    return type;
  }

  public List<AttributeValue> getValues() {
    return values;
  }
}
