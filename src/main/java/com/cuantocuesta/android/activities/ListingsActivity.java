package com.cuantocuesta.android.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ItemDetail;
import com.cuantocuesta.android.views.ItemStackableView;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.ColorsProvider;
import com.cuantocuesta.domain.ListingsStream;
import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.ResultContainer;
import com.google.common.base.Function;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.Arrays;
import java.util.List;

public class ListingsActivity extends MainContentFragment<ResultContainer, Meli, Listing> {

  protected ListingsStream listingsStream;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    super.onCreateFrame(savedInstanceState, view);

    getStackableView().build(this);
    getStackableView().setOnShowDetail(new Function<ItemStackableView, ItemStackableView>() {
      @Override
      public ItemStackableView apply(final ItemStackableView input) {
        if (input.getCurrent() != null)
          loadAndShowDetails(input);

        return input;
      }
    });

  }

  private void loadAndShowDetails(final ItemStackableView input) {
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
        getDetailView().update(input.getCurrent());
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    ColorsProvider.getInstance().loadColors(getSpiceManager(), getString(R.string.meli_clothing_category_example));
  }

  @Override
  protected Class<ResultContainer> getResponseClass() {
    return ResultContainer.class;
  }

  @Override
  protected Class<Meli> getServiceClass() {
    return Meli.class;
  }

  @Override
  protected ResultContainer performQuery(Meli service) {
    return new ResultContainer(createOrGetListingsStream(service).getMoreListings());
  }

  @Override
  protected List<Listing> getResultsFromResponse(ResultContainer result) {
    return result.getResults();
  }

  @Override
  protected SpiceListItemView<Listing> createView(Context context) {
    return new ListingView(context, listingsStream, this);
  }

  protected ListingsStream createOrGetListingsStream(Meli service) {
    if (this.listingsStream == null) {
      this.listingsStream = new ListingsStream(
        service,
        getString(R.string.meli_site),
        Arrays.asList("MLA109276", "MLA109085", "MLA109282")
      );
    }

    return listingsStream;
  }

  public ListingsStream getListingsStream() {
    return listingsStream;
  }

  public ListingView pop(){
    if(getListingsListView().getAdapter().getCount() == 0) return null;
    ListingView listingView = new ListingView(this.getActivity(), getListingsStream(), this);
    getListingsListView().getAdapter().getView(0, listingView, null);
    return listingView;
  }

  @Override
  protected void updateListViewContent(List<Listing> items) {
    super.updateListViewContent(items);
    this.getStackableView().populateIfEmpty(this);
  }
}
