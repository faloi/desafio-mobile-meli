package com.cuantocuesta.domain.meli.dtos;

import com.cuantocuesta.android.applicationModels.Displayable;

public class Listing implements Displayable {
  public String id;
  public String title;
  public String subtitle;
  public java.lang.Double price;
  public String thumbnail;

  public String getPermalink() {
    return permalink;
  }

  public String permalink;

  @Override
  public String getThumbnail() {
    return thumbnail;
  }
  @Override
  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
  public Double getPrice() {
    return price;
  }
}
