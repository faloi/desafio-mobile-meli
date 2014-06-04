package com.cuantocuesta.android.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.cuantocuesta.R;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ListingView extends RelativeLayout implements SpiceListItemView<Listing> {
  private TextView titleTextView;
  private TextView priceTextView;
  private ImageView thumbImageView;
  private Listing listing;

  public ListingView(Context context) {
    super(context);
    inflateView(context);
  }

  private void inflateView(final Context context) {
    LayoutInflater.from(context).inflate(R.layout.view_cell_github, this);
    this.titleTextView = (TextView) this.findViewById(R.id.user_name_textview);
    this.priceTextView = (TextView) this.findViewById(R.id.github_content_textview);
    this.thumbImageView = (ImageView) this.findViewById(R.id.octo_thumbnail_imageview);

    this.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String intentUri = String.format("meli://item?id=%s", listing.getId());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(intentUri));
        context.startActivity(intent);
      }
    });
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
}
