package com.cuantocuesta.android.activities;

import android.widget.ListAdapter;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.adapters.CategoryToPictureAdapter;
import com.cuantocuesta.android.adapters.ListingToPictureAdapter;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.dtos.Category;
import com.cuantocuesta.domain.meli.dtos.ChildrenCategory;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

public class CategoriesActivity extends ListSpiceActivity<Category.List, Meli, Category> {
  @Override
  protected Class<Category.List> getResponseClass() {
    return Category.List.class;
  }

  @Override
  protected Class<Meli> getServiceClass() {
    return Meli.class;
  }

  @Override
  protected Category.List performQuery(Meli meli) {
    return this.getClothingCategories(meli);
  }

  private Category.List getClothingCategories(final Meli meli) {
    Category category = meli.getCategory(Meli.CLOTHING_BASE_CATEGORY_ID);
    List<ChildrenCategory> childrenCategories = category.getChildrenCategories();

    Category.List categories = new Category.List();
    categories.setCategories(
      Lists.newArrayList(Iterables.transform(childrenCategories, new Function<ChildrenCategory, Category>() {
        @Override
        public Category apply(ChildrenCategory childrenCategory) {
          return meli.getCategory(childrenCategory.getId());
        }
      }))
    );

    return categories;
  }

  @Override
  protected ListAdapter getAdapter(List<Category> items) {
    return new CategoryToPictureAdapter(this, getSpiceManagerBinary(), items);
  }

  @Override
  protected List<Category> getResultsFromResponse(Category.List result) {
    return result.getCategories();
  }
}
