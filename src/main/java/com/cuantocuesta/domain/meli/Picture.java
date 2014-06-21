package com.cuantocuesta.domain.meli;

public class Picture {
  public String id;
  public String url;

  public Picture() {
  }

  public Picture(String url) {
    this.url = url;
  }

  public Picture(String id, String url) {
    this(url);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }
}
