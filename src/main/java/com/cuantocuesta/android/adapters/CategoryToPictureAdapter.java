package com.cuantocuesta.android.adapters;

import android.content.Context;
import com.cuantocuesta.android.views.CategoryView;
import com.cuantocuesta.domain.meli.dtos.Category;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

import java.util.List;

public class CategoryToPictureAdapter extends ModelToPictureAdapter<Category> {
  public CategoryToPictureAdapter(Context context, OkHttpBitmapSpiceManager spiceManagerBitmap, List<Category> items) {
    super(context, spiceManagerBitmap, items);
  }

  @Override
  protected SpiceListItemView<Category> getView(Context context) {
    return new CategoryView(context);
  }

  @Override
  protected String getId(Category category) {
    return category.getId();
  }

  @Override
  protected String getPictureUrl(Category category) {
    return category.getPicture();
  }
}
