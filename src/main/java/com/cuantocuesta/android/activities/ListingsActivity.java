package com.cuantocuesta.android.activities;

import android.content.Context;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.ListingsStream;
import com.cuantocuesta.domain.meli.ResultContainer;
import com.cuantocuesta.domain.meli.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.Arrays;
import java.util.List;

public class ListingsActivity extends ListSpiceActivity<ResultContainer, Meli, Listing> {

  protected ListingsStream listingsStream;

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
}
