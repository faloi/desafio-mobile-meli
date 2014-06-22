package com.cuantocuesta.android.activities;

import android.content.Context;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.ListingsStream;
import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.ResultContainer;
import com.octo.android.robospice.spicelist.SpiceListItemView;

import java.util.List;

public class MyClothesFragment extends ListSpiceActivity<ResultContainer, Meli, Listing> {
  @Override
  protected Class<ResultContainer> getResponseClass() {
    return ResultContainer.class;
  }

  @Override
  protected Class<Meli> getServiceClass() {
    return Meli.class;
  }

  @Override
  protected ResultContainer performQuery(Meli meli) {
    return new ResultContainer(ListingsStream.getInstance().getLikedListings());
  }

  @Override
  protected List<Listing> getResultsFromResponse(ResultContainer result) {
    return null;
  }

  @Override
  protected SpiceListItemView<Listing> createView(Context context) {
    return null;
  }
}
