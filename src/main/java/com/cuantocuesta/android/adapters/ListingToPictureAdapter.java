package com.cuantocuesta.android.adapters;

import android.content.Context;
import android.view.ViewGroup;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.request.simple.IBitmapRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpSpiceArrayAdapter;

import java.io.File;
import java.util.List;

import com.octo.android.robospice.request.okhttp.simple.OkHttpBitmapRequest;

public class ListingToPictureAdapter extends OkHttpSpiceArrayAdapter<Listing> {
  public ListingToPictureAdapter(Context context, OkHttpBitmapSpiceManager spiceManagerBitmap, List<Listing> items) {
    super(context, spiceManagerBitmap, items);
  }

  @Override
  public SpiceListItemView<Listing> createView(Context context, ViewGroup viewGroup) {
    return new ListingView(getContext());
  }

  @Override
  public IBitmapRequest createRequest(Listing listing, int imageIndex, int requestImageWidth, int requestImageHeight) {
    File tempFile = new File(getContext().getCacheDir(), "THUMB_IMAGE_TEMP_" + listing.getId());
    return new OkHttpBitmapRequest(listing.getThumbnail(), requestImageWidth, requestImageHeight, tempFile);
  }
}
