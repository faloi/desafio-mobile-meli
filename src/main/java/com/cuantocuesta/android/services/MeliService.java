package com.cuantocuesta.android.services;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class MeliService extends RetrofitGsonSpiceService {

  private final static String BASE_URL = "https://api.mercadolibre.com";

  @Override
  public void onCreate() {
    super.onCreate();
    addRetrofitInterface(Meli.class);
  }

  @Override
  protected String getServerUrl() {
    return BASE_URL;
  }

}

