package com.cuantocuesta.domain.meli;

public class AttributeValue {
  public String name;
  public Metadata metadata;

  public static AttributeValue color(String name, String rgb) {
    AttributeValue value = new AttributeValue();
    value.name = name;
    value.metadata = new Metadata(rgb);

    return value;
  }

  public String getName() {
    return name;
  }

  public String getRgb() {
    return getMetadata().getRgb();
  }

  private Metadata getMetadata() {
    return metadata;
  }
}