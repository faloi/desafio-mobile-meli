package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.view.View;
import com.cuantocuesta.R;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ItemDetail;
import com.cuantocuesta.android.views.ItemStackableView;
import com.cuantocuesta.domain.meli.Listing;
import com.google.common.base.Function;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

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
      public ItemStackableView apply(final ItemStackableView input) {
        final Listing listingWithoutVariations = input.getCurrent().getListing();

        getSpiceManager().execute(new RetrofitSpiceRequest<Listing, Meli>(Listing.class, Meli.class) {
          @Override
          public Listing loadDataFromNetwork() throws Exception {
            return getService().getVariations(listingWithoutVariations.getId());
          }
        }, new RequestListener<Listing>() {
          @Override
          public void onRequestFailure(SpiceException spiceException) {

          }

          @Override
          public void onRequestSuccess(Listing listing) {
            listingWithoutVariations.addVariations(listing.getVariations());
            detailView.update(input.getCurrent());
          }
        });

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