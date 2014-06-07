package com.cuantocuesta.android.views;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuantocuesta.R;
import com.cuantocuesta.android.ListingsStream;
import com.cuantocuesta.android.activities.ListingsActivity;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;


public class ListingView extends RelativeLayout implements SpiceListItemView<Listing> {
  private TextView titleTextView;
  private TextView priceTextView;
  private ImageView thumbImageView;
  private Listing listing;
  private ListingsStream listingsStream;
  private ListingsActivity parent;

  public ListingView(Context context, ListingsStream listingsStream, ListingsActivity parent) {
    super(context);
    this.listingsStream = listingsStream;
    this.parent = parent;
    inflateView(context);
  }

  private void inflateView(final Context context) {
    LayoutInflater.from(context).inflate(R.layout.list_item, this);
    this.titleTextView = (TextView) this.findViewById(R.id.user_name_textview);
    this.priceTextView = (TextView) this.findViewById(R.id.github_content_textview);
    this.thumbImageView = (ImageView) this.findViewById(R.id.octo_thumbnail_imageview);

//    this.setOnClickListener(new OpenMeliListing(context));

    getLikeButton().setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        listingsStream.registerLike(listing);
        removeMyself();
      }
    });

    getDislikeButton().setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        listingsStream.registerDislike(listing);
        removeMyself();
      }
    });
  }

  public Button getLikeButton() {
    return (Button) this.findViewById(R.id.like_button);
  }

  public Button getDislikeButton() {
    return (Button) this.findViewById(R.id.dislike_button);
  }

  @Override
  public Listing getData() {
    return listing;
  }

  @Override
  public ImageView getImageView(int imageIndex) {
    return thumbImageView;
  }

  @Override
  public int getImageViewCount() {
    return 1;
  }

  @Override
  public void update(Listing listing) {
    this.listing = listing;
    titleTextView.setText(listing.getTitle());
    priceTextView.setText(String.valueOf(listing.getPrice()));
  }

  private void removeMyself() {
    parent.removeItem(this.listing);
  }

  private class OpenMeliListing implements OnClickListener {
    private final Context context;

    public OpenMeliListing(Context context) {
      this.context = context;
    }

    @Override
    public void onClick(View view) {
      try {
        openMeliApp();
      } catch (ActivityNotFoundException e) {
        openBrowser();
      }
    }

    private void openBrowser() {
      callIntent(listing.getPermalink());
    }

    private void openMeliApp() {
      callIntent(String.format("meli://item?id=%s", listing.getId()));
    }

    private void callIntent(String intentUri) {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(intentUri));
      context.startActivity(intent);
    }
  }
}
