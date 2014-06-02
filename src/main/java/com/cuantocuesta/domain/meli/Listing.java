package com.cuantocuesta.domain.meli;

public class Listing {
  private Double price;
  private String categoryId;

  public Listing(Double price, String categoryId) {
    this.price = price;
    this.categoryId = categoryId;
  }

  public Listing(Double price) {
    this(price, "");
  }

  public Double getPrice() {
    return price;
  }

  public Boolean categoryIs(String anotherCategory) {
    return categoryId.equals(anotherCategory);
  }
}
