package com.cuantocuesta.android.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuantocuesta.R;
import com.cuantocuesta.domain.meli.Category;
import com.octo.android.robospice.spicelist.SpiceListItemView;

public class CategoryView extends RelativeLayout implements SpiceListItemView<Category> {
  private TextView titleTextView;
  private ImageView thumbImageView;
  private Category category;

  public CategoryView(Context context) {
    super(context);
    inflateView(context);
  }

  private void inflateView(Context context) {
    LayoutInflater.from(context).inflate(R.layout.list_item_category, this);
    this.titleTextView = (TextView) this.findViewById(R.id.user_name_textview);
    this.thumbImageView = (ImageView) this.findViewById(R.id.octo_thumbnail_imageview);
  }

  @Override
  public Category getData() {
    return category;
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
  public void update(Category category) {
    this.category = category;
    titleTextView.setText(category.getName());
  }

  public Category getCategory() {
    return category;
  }
}
