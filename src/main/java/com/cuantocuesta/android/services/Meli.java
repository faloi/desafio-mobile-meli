package com.cuantocuesta.android.services;

import com.cuantocuesta.domain.meli.dtos.Category;
import com.cuantocuesta.domain.meli.dtos.Example;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Meli {
  static final String CLOTHING_BASE_CATEGORY_ID = "MLA1430";

  @GET("/sites/MLA/search")
  Example search(@Query("q") String query);

  @GET("/categories/{categoryId}")
  Category getCategory(@Path("categoryId") String id);
}