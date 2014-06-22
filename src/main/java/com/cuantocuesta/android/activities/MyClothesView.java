package com.cuantocuesta.android.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuantocuesta.R;
import com.cuantocuesta.android.views.OpenMeliListing;
import com.cuantocuesta.domain.meli.Listing;
import com.octo.android.robospice.spicelist.SpiceListItemView;

public class MyClothesView extends RelativeLayout implements SpiceListItemView<Listing> {
  private final Context context;
  private Listing data;
  private ImageView thumbImageView;
  private TextView titleTextView;

  public MyClothesView(Context context) {
    super(context);

    this.context = context;
    inflateView(context);
  }

  private void inflateView(final Context context) {
    LayoutInflater.from(context).inflate(R.layout.list_item_category, this);
    this.titleTextView = (TextView) this.findViewById(R.id.user_name_textview);
    this.thumbImageView = (ImageView) this.findViewById(R.id.octo_thumbnail_imageview);
  }

  @Override
  public Listing getData() {
    return data;
  }

  @Override
  public ImageView getImageView(int imageIndex) {
    return this.thumbImageView;
  }

  @Override
  public int getImageViewCount() {
    return 1;
  }

  @Override
  public void update(Listing data) {
    this.data = data;

    thumbImageView.setOnClickListener(new OpenMeliListing(context, data));
    titleTextView.setText(data.getTitle());
  }
}
