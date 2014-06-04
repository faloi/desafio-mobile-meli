package com.cuantocuesta.android.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

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

  @Override
  protected Converter createConverter() {
    Gson gson = new GsonBuilder()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create();

    return new GsonConverter(gson);
  }
}

