package com.cuantocuesta.android.services;

import com.cuantocuesta.domain.meli.Attribute;
import com.cuantocuesta.domain.meli.Category;
import com.cuantocuesta.domain.meli.Listing;
import com.cuantocuesta.domain.meli.ResultContainer;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

public interface Meli {
  @GET("/categories/{id}?attributes=id,name,picture,children_categories")
  Category getCategory(@Path("id") String id);

  @GET("/sites/{site}/search")
  ResultContainer searchByCategory(
    @Path("site") String site,
    @Query("category") String category,
    @Query("offset") int offset,
    @Query("limit") int limit
  );

  @GET("/items?attributes=id,pictures,title,price")
  List<Listing> getListingsDetails(@Query("ids") String commaSeparatedIds);

  @GET("/categories/{id}/attributes")
  Attribute.List getAttributesOfCategory(@Path("id") String categoryId);

  @GET("/items/{id}?attributes=variations")
  Listing getVariations(@Path("id") String id);
}