package com.cuantocuesta.android.activities;

import android.widget.ListAdapter;
import com.cuantocuesta.android.activities.templates.ListSpiceActivity;
import com.cuantocuesta.android.adapters.ListingToPictureAdapter;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Listing;

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
    return service.search(query);
  }

  @Override
  protected ListAdapter getAdapter(List<Listing> items) {
    return new ListingToPictureAdapter(this, getSpiceManagerBinary(), items);
  }

  @Override
  protected List<Listing> getResultsFromResponse(Example result) {
    return result.getResults();
  }
}
