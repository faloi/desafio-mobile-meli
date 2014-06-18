package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.view.View;

import com.cuantocuesta.R;
import com.cuantocuesta.android.views.ItemStackableView;
import com.cuantocuesta.domain.meli.Listing;

import java.util.List;

public class MainContentFragment  extends ListingsActivity {
  public static final String ARG_OS= "OS";
  private ItemStackableView stackableView;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    super.onCreateFrame(savedInstanceState, view);
    stackableView = (ItemStackableView) findViewById(R.id.stackable_view);
    stackableView.build(this);
  }

  @Override
  protected void updateListViewContent(List<Listing> items) {
    super.updateListViewContent(items);
    stackableView.populate(this);
  }

  @Override
  protected int layoutId() {
    return R.layout.home_fragment;
  }


}