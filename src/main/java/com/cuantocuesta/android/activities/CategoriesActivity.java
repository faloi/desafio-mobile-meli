package com.cuantocuesta.android.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.cuantocuesta.R;
import com.cuantocuesta.android.SwipeDetector;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.CategoryView;
import com.cuantocuesta.domain.meli.Category;
import com.cuantocuesta.domain.meli.ChildrenCategory;
import com.cuantocuesta.domain.meli.Listing;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CategoriesActivity extends ListSpiceActivity<Category.List, Meli, Category> {

  private Collection<Category> selectedCategories = new ArrayList<Category>();

  @Override
  protected Class<Category.List> getResponseClass() {
    return Category.List.class;
  }

  @Override
  protected int layoutId() {
    return R.layout.fragment_category;
  }

  @Override
  protected Class<Meli> getServiceClass() {
    return Meli.class;
  }

  @Override
  protected Category.List performQuery(Meli meli) {
    return this.getClothingCategories(meli);
  }

  @Override
  protected List<Category> getResultsFromResponse(Category.List result) {
    return result.getCategories();
  }

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    super.onCreateFrame(savedInstanceState, view);
    final SwipeDetector swipeDetector = new SwipeDetector();
    listingsListView.setOnTouchListener(swipeDetector);
    listingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(swipeDetector.swipeDetected()) {
          if(swipeDetector.getAction() == SwipeDetector.Action.RL) {
            removeItem(((CategoryView)view).getCategory());
          } else if(swipeDetector.getAction() == SwipeDetector.Action.LR){
            Category category = ((CategoryView) view).getCategory();
            selectedCategories.add(category);
            removeItem(category);
          }

          if(listingsListView.getChildCount() == 0){
            goToNextWindow();
          }
      }
    }});


  }

  @Override
  protected void updateListViewContent(List<Category> items) {
    if(items.isEmpty()) goToNextWindow();
    super.updateListViewContent(items);
  }

  public void goToNextWindow(){
//    ((LauncherActivity)this.getActivity()).
  }

  @Override
  protected SpiceListItemView<Category> createView(Context context) {
    return new CategoryView(context);
  }

  private Category.List getClothingCategories(final Meli meli) {
    Category category = meli.getCategory(getString(R.string.meli_base_clothing_category));
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

}
