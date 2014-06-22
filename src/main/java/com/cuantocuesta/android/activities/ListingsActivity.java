package com.cuantocuesta.android.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.cuantocuesta.R;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ItemStackableView;
import com.cuantocuesta.android.views.LikeableView;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.ColorsProvider;
import com.cuantocuesta.domain.ListingsStream;
import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.ResultContainer;
import com.cuantocuesta.domain.observers.LikingLearningObserver;
import com.google.common.base.Function;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.Arrays;
import java.util.List;

public class ListingsActivity extends MainContentFragment<ResultContainer, Meli, Listing> implements DisplayableQueue, LikingLearningObserver {

  protected ListingsStream listingsStream;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    super.onCreateFrame(savedInstanceState, view);

    getStackableView().build(this);
    getStackableView().setOnShowDetail(new Function<ItemStackableView<Listing>, ItemStackableView<Listing>>() {
      @Override
      public ItemStackableView<Listing> apply(final ItemStackableView<Listing> input) {
        if (input.getCurrent() != null)
          loadAndShowDetails(input);

        return input;
      }
    });

  }

  private void loadAndShowDetails(final ItemStackableView<Listing> input) {
    final Listing listingWithoutVariations = input.getCurrent().getItem();

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
        Arrays.asList("MLA109085"),
        Arrays.<LikingLearningObserver>asList(this)
      );
    }

    return listingsStream;
  }

  public ListingsStream getListingsStream() {
    return listingsStream;
  }

  public LikeableView<Listing> pop(){
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

  @Override
  public void notifyListingsLikeThisWillBeExcluded(Listing listing) {
    Toast.makeText(
      getActivity().getApplicationContext(),
      getString(R.string.no_more_listings_like_this, listing.getTitle().toLowerCase()),
      Toast.LENGTH_SHORT
    ).show();
  }
}
