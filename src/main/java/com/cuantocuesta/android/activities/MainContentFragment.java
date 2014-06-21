package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.view.View;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.applicationModels.Displayable;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ItemDetail;
import com.cuantocuesta.android.views.ItemStackableView;
import com.cuantocuesta.domain.meli.Listing;
import com.google.common.base.Function;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

public abstract class MainContentFragment<TResponse, TService, TModel extends Displayable>  extends ListSpiceActivity<TResponse, TService, TModel> implements CanHandleBackButton {
  public static final String ARG_OS= "OS";
  private ItemStackableView stackableView;
  private ItemDetail detailView;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    super.onCreateFrame(savedInstanceState, view);
    stackableView = (ItemStackableView) findViewById(R.id.stackable_view);
    detailView = (ItemDetail) findViewById(R.id.detail_view);
  }

  @Override
  protected int layoutId() {
    return R.layout.home_fragment;
  }
  
  public ItemStackableView getStackableView() {
    return stackableView;
  }

  public ItemDetail getDetailView() {
    return detailView;
  }

  public boolean onBackPressed(){
    boolean shouldPropagate = false;
    if(detailView.getVisibility() == View.VISIBLE){
      detailView.hide();
      shouldPropagate = true;
    }
    return shouldPropagate;
  }
}