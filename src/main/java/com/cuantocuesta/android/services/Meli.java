package com.cuantocuesta.android.services;

import com.cuantocuesta.domain.meli.dtos.Category;
import com.cuantocuesta.domain.meli.dtos.Example;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Meli {
  @GET("/sites/MLA/search")
  Example search(@Query("q") String query);

  @GET("/categories/{categoryId}?attributes=id,name,picture,children_categories")
  Category getCategory(@Path("categoryId") String id);
}