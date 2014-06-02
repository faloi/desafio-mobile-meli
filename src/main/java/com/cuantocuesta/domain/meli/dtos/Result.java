package com.cuantocuesta.domain.meli.dtos;

public class Result {
  private String id;
  private String title;
  private String subtitle;
  private Seller seller;
  private java.lang.Double price;
  private java.lang.Integer sold_quantity;
  private String listing_type_id;
  private String condition;
  private String permalink;
  private String thumbnail;
  private Address address;
  private Shipping shipping;
  private Seller_address seller_address;
  private String category_id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public Seller getSeller() {
    return seller;
  }

  public void setSeller(Seller seller) {
    this.seller = seller;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getSold_quantity() {
    return sold_quantity;
  }

  public void setSold_quantity(Integer sold_quantity) {
    this.sold_quantity = sold_quantity;
  }

  public String getListing_type_id() {
    return listing_type_id;
  }

  public void setListing_type_id(String listing_type_id) {
    this.listing_type_id = listing_type_id;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Shipping getShipping() {
    return shipping;
  }

  public void setShipping(Shipping shipping) {
    this.shipping = shipping;
  }

  public Seller_address getSeller_address() {
    return seller_address;
  }

  public void setSeller_address(Seller_address seller_address) {
    this.seller_address = seller_address;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }
}
