package com.cuantocuesta.android.adapters;

import android.content.Context;
import android.view.ViewGroup;
import com.cuantocuesta.android.views.CategoryView;
import com.cuantocuesta.domain.meli.dtos.Category;
import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.request.simple.IBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;
import java.util.List;

public class CategoryToPictureAdapter extends OkHttpSpiceArrayAdapter<Category> {
  public CategoryToPictureAdapter(Context context, OkHttpBitmapSpiceManager spiceManagerBitmap, List<Category> items) {
    super(context, spiceManagerBitmap, items);
  }

  @Override
  public SpiceListItemView<Category> createView(Context context, ViewGroup viewGroup) {
    return new CategoryView(getContext());
  }

  @Override
  public IBitmapRequest createRequest(Category category, int imageIndex, int requestImageWidth, int requestImageHeight) {
    File tempFile = new File(getContext().getCacheDir(), "THUMB_IMAGE_TEMP_" + category.getId());
    return new OkHttpBitmapRequest(category.getPicture(), requestImageWidth, requestImageHeight, tempFile);
  }
}
