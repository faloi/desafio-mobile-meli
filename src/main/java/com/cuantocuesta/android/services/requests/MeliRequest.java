package com.cuantocuesta.android.services.requests;

import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.dtos.Example;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class MeliRequest extends RetrofitSpiceRequest<Example, Meli> {
  private String query;

  public MeliRequest(String query) {
    super(Example.class, Meli.class);
    this.query = query;
  }

  @Override
  public Example loadDataFromNetwork() {
    return getService().search(query);
  }
}
