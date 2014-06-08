package com.cuantocuesta.domain.meli;

import com.cuantocuesta.android.applicationModels.Displayable;

import java.util.ArrayList;
import java.util.List;

public class Listing implements Displayable {
  public String id;
  public String title;
  public java.lang.Double price;
  public List<Picture> pictures;

  public Listing() {
    this.pictures = new ArrayList<Picture>();
  }

  public Listing(String id) {
    this();
    this.id = id;
  }

  public String getPermalink() {
    return permalink;
  }

  public String permalink;

  public List<Picture> getPictures() {
    return pictures;
  }

  @Override
  public String getThumbnail() {
    return getPictures().get(0).url;
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

  public void addPictures(String... pictures) {
    for (String p : pictures) {
      this.getPictures().add(new Picture(p));
    }
  }
}
