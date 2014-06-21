package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.view.View;

import com.cuantocuesta.R;
import com.cuantocuesta.android.views.ItemDetail;
import com.cuantocuesta.android.views.ItemStackableView;
import com.cuantocuesta.domain.meli.Listing;
import com.google.common.base.Function;

import java.util.List;

public class MainContentFragment  extends ListingsActivity {
  public static final String ARG_OS= "OS";
  private ItemStackableView stackableView;
  private ItemDetail detailView;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    super.onCreateFrame(savedInstanceState, view);
    stackableView = (ItemStackableView) findViewById(R.id.stackable_view);
    detailView = (ItemDetail) findViewById(R.id.detail_view);

    stackableView.build(this);
    stackableView.setOnShowDetail(new Function<ItemStackableView, ItemStackableView>() {
      @Override
      public ItemStackableView apply(ItemStackableView input) {
        detailView.update(input.getCurrent());
        return input;
      }
    });
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