package com.cuantocuesta.android.activities;

import android.view.View;
import android.widget.Toast;
import com.cuantocuesta.android.adapters.ListingToPictureAdapter;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

public class ListingsActivity extends ListSpiceActivity<Example, Meli> {

  // ============================================================================================
  // ATTRIBUTES
  // ============================================================================================

  private String query = "campera de cuero";
  private ListingToPictureAdapter listingToPictureAdapter;

  // ============================================================================================
  // ACTIVITY LIFE CYCLE
  // ============================================================================================

  @Override
  protected void onStart() {
    super.onStart();
    loadListings();
  }

  @Override
  protected Class<Example> getModelClass() {
    return Example.class;
  }

  @Override
  protected Class<Meli> getServiceClass() {
    return Meli.class;
  }

  @Override
  protected Example performQuery(Meli service) {
    return service.search(query);
  }

  // ============================================================================================
  // PRIVATE METHODS
  // ============================================================================================

  private void updateListViewContent(List<Listing> items) {
    listingToPictureAdapter = new ListingToPictureAdapter(this, getSpiceManagerBinary(), items);
    listingsListView.setAdapter(listingToPictureAdapter);

    loadingView.setVisibility(View.GONE);
    listingsListView.setVisibility(View.VISIBLE);
  }

  private void loadListings() {
    setProgressBarIndeterminateVisibility(true);
    getSpiceManager().execute(request, "meli", DurationInMillis.ONE_MINUTE, new ItemsRequestListener());
  }

  // ============================================================================================
  // INNER CLASSES
  // ============================================================================================

  public final class ItemsRequestListener implements RequestListener<Example> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {
      setProgressBarIndeterminateVisibility(false);
      Toast.makeText(ListingsActivity.this, "Ha ocurrido un error al cargar las publicaciones", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(Example result) {
      setProgressBarIndeterminateVisibility(false);
      updateListViewContent(result.getListings());
    }
  }
}
