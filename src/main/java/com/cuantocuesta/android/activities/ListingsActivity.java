package com.cuantocuesta.android.activities;

import android.content.Context;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.ListingsStream;
import com.cuantocuesta.domain.meli.Example;
import com.cuantocuesta.domain.meli.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.Arrays;
import java.util.List;

public class ListingsActivity extends ListSpiceActivity<Example, Meli, Listing> {

  private ListingsStream listingsStream;

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
    return new Example(createOrGetListingsStream(service).getMoreListings());
  }

  @Override
  protected List<Listing> getResultsFromResponse(Example result) {
    return result.getResults();
  }

  @Override
  protected SpiceListItemView<Listing> createView(Context context) {
    return new ListingView(context, listingsStream, this);
  }

  private ListingsStream createOrGetListingsStream(Meli service) {
    if (this.listingsStream == null) {
      this.listingsStream = new ListingsStream(
        service,
        getString(R.string.meli_site),
        Arrays.asList("MLA109276", "MLA109085", "MLA109282")
      );
    }

    return listingsStream;
  }
}
