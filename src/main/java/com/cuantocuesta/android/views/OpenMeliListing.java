package com.cuantocuesta.android.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.view.View;
import com.cuantocuesta.domain.meli.Listing;

public class OpenMeliListing implements View.OnClickListener {
  private final Context context;
  private final Listing listing;

  public OpenMeliListing(Context context, Listing listing) {
    this.context = context;
    this.listing = listing;
  }

  @Override
  public void onClick(View view) {
    try {
      listing.openMeliApp(context);
    } catch (ActivityNotFoundException e) {
      openBrowser();
    }
  }

  private void openBrowser() {
    listing.callIntent(context, listing.getPermalink());
  }

}
