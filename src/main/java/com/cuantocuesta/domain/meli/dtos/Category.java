package com.cuantocuesta.domain.meli.dtos;

import java.util.ArrayList;

public class Category {
  public String id;
  public String name;
  public String picture;
  public java.util.List<ChildrenCategory> childrenCategories;

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getPicture() {
    return picture;
  }

  public java.util.List<ChildrenCategory> getChildrenCategories() {
    return childrenCategories;
  }

  public static class List {
    private static final long serialVersionUID = 6836514467436078182L;

    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
      return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
      this.categories = categories;
    }

    public static long getSerialversionuid() {
      return serialVersionUID;
    }
  }

}
