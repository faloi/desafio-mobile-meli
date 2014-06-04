package com.cuantocuesta.domain.meli.dtos;

public class Listing {
  public String getId() {
    return id;
  }

  public String id;

  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public String title;
  public String subtitle;
  public Seller seller;
  public java.lang.Double price;
  public java.lang.Integer sold_quantity;
  public String listing_type_id;
  public String condition;
  public String permalink;

  public String getThumbnail() {
    return thumbnail;
  }

  public String thumbnail;
  public Address address;
  public Shipping shipping;
  public Seller_address seller_address;

  public String getCategory_id() {
    return category_id;
  }

  public String category_id;
}
