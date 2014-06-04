package com.cuantocuesta.android.services;

import com.cuantocuesta.domain.meli.dtos.Example;
import retrofit.http.GET;
import retrofit.http.Query;

public interface Meli {
  @GET("/sites/MLA/search")
  Example search(@Query("q") String query);
}