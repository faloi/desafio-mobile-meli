package com.cuantocuesta.android.services;

import com.cuantocuesta.domain.meli.Category;
import com.cuantocuesta.domain.meli.ResultContainer;
import com.cuantocuesta.domain.meli.Listing;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

public interface Meli {
  @GET("/categories/{categoryId}?attributes=id,name,picture,children_categories")
  Category getCategory(@Path("categoryId") String id);

  @GET("/sites/{site}/search")
  ResultContainer searchByCategory(
    @Path("site") String site,
    @Query("category") String category,
    @Query("offset") int offset,
    @Query("limit") int limit
  );

  @GET("/items?attributes=id,pictures,title,price")
  List<Listing> getListingsDetails(@Query("ids") String commaSeparatedIds);
}