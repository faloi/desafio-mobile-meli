package com.cuantocuesta.domain.meli;

import java.util.ArrayList;
import java.util.Arrays;

public class Attribute {
  public String type;
  public java.util.List<AttributeValue> values;

  public Attribute(String type) {
    this.type = type;
  }

  public Attribute(String type, AttributeValue... values) {
    this(type);
    this.values = Arrays.asList(values);
  }

  public boolean isColor() {
    return this.getType() != null && this.getType().equals("color");
  }

  public String getType() {
    return type;
  }

  public java.util.List<AttributeValue> getValues() {
    return values;
  }

  public static class List extends ArrayList<Attribute> {
  }
}
