package com.cuantocuesta.android.adapters;

import android.content.Context;
import com.cuantocuesta.android.views.ListingView;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

import java.util.List;

public class ListingToPictureAdapter extends ModelToPictureAdapter<Listing> {
  public ListingToPictureAdapter(Context context, OkHttpBitmapSpiceManager spiceManagerBitmap, List<Listing> items) {
    super(context, spiceManagerBitmap, items);
  }

  @Override
  protected SpiceListItemView<Listing> getView(Context context) {
    return new ListingView(getContext());
  }

  @Override
  protected String getId(Listing listing) {
    return listing.getId();
  }

  @Override
  protected String getPictureUrl(Listing listing) {
    return listing.getThumbnail();
  }
}
