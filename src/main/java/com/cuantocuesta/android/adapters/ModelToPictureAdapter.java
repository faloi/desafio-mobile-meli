package com.cuantocuesta.android.adapters;

import android.content.Context;
import android.view.ViewGroup;
import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;
import com.octo.android.robospice.request.simple.IBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;
import java.util.List;

public abstract class ModelToPictureAdapter<TModel> extends OkHttpSpiceArrayAdapter<TModel> {
  public ModelToPictureAdapter(Context context, OkHttpBitmapSpiceManager spiceManagerBinary, List<TModel> objects) {
    super(context, spiceManagerBinary, objects);
  }

  @Override
  public SpiceListItemView<TModel> createView(Context context, ViewGroup viewGroup) {
    return getView(getContext());
  }

  @Override
  public IBitmapRequest createRequest(TModel model, int imageIndex, int requestImageWidth, int requestImageHeight) {
    File tempFile = new File(getContext().getCacheDir(), "THUMB_IMAGE_TEMP_" + getId(model));
    return new OkHttpBitmapRequest(getPictureUrl(model), requestImageWidth, requestImageHeight, tempFile);
  }

  protected abstract SpiceListItemView<TModel> getView(Context context);

  protected abstract String getId(TModel model);

  protected abstract String getPictureUrl(TModel model);
}
