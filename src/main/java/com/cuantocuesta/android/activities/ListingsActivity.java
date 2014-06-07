package com.cuantocuesta.android.activities;

import android.content.Context;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.List;

public class ListingsActivity extends ListSpiceActivity<Example, Meli, Listing> {

  private String query = "campera de cuero";

  @Override
  protected Class<Example> getResponseClass() {
    return Example.class;
  }

  @Override
  protected Class<Meli> getServiceClass() {
    return Meli.class;
  }

  @Override
  protected Example performQuery(Meli service) {
    return service.search(getString(R.string.meli_site), query);
  }

  @Override
  protected List<Listing> getResultsFromResponse(Example result) {
    return result.getResults();
  }

  @Override
  protected SpiceListItemView<Listing> createView(Context context) {
    return new ListingView(context);
  }
}
