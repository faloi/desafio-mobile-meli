package com.cuantocuesta.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuantocuesta.R;
import com.cuantocuesta.domain.ColorsProvider;
import com.cuantocuesta.domain.meli.CombinatedColor;
import com.cuantocuesta.domain.meli.Listing;
import com.google.common.base.Joiner;

public class ItemDetail extends RelativeLayout {
  private final ImageView listingImage;
  private final TextView price;
  private final TextView talle;
  private LikeableView<Listing> listingView;
  private final ViewGroup colorsContainer;

  public ItemDetail(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.listing_detail, this, true);
    listingImage = (ImageView) findViewById(R.id.listing_detail_main_image);
    price = (TextView) findViewById(R.id.listing_detail_price);
    talle = (TextView) findViewById(R.id.listing_detail_talle);
    colorsContainer = (ViewGroup) findViewById(R.id.color_selector_container);

    findViewById(R.id.listing_detail_button_carrito).setOnClickListener(new BuyClick());
    findViewById(R.id.listing_detail_button_back).setOnClickListener(new BackClick());
  }

  public void update(LikeableView<Listing> listing) {
    listingView = listing;

    ImageView view = listing.getImageView();
    listingImage.setImageDrawable(view.getDrawable());

    price.setText("$ " + listing.getItem().getPrice());
    this.updateColors(listing.getItem());
    this.setAnimation(AnimationUtils.loadAnimation(this.getContext(), R.anim.go_up));

    Joiner joiner = Joiner.on(" ").skipNulls();
    talle.setText(getContext().getString(R.string.talle, joiner.join(listing.getItem().getSizes())));

      findViewById(R.id.badge_free_shipping).setVisibility(listing.getItem().hasFreeShipping()? VISIBLE:GONE);

      findViewById(R.id.badge_vendedor_oficial).setVisibility(listing.getItem().isFromOfficialStore() ? VISIBLE : GONE);

    this.show();
  }

  public void show() {
    ItemDetail.this.setVisibility(VISIBLE);
    this.animate();
  }

  private void updateColors(Listing listing) {
    colorsContainer.removeAllViews();

    for (CombinatedColor c : listing.getColors(ColorsProvider.getInstance().getAvailableColors()))
      addColorView(c);
  }

  private void addColorView(CombinatedColor color) {
    colorsContainer.addView(new ItemColor(this.getContext()).color(color));
  }

  public void hide() {
    Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.go_down);
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
        ItemDetail.this.setVisibility(GONE);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    this.setAnimation(animation);
    ItemDetail.this.setVisibility(GONE);
    this.animate();
  }

  class BackClick implements OnClickListener {

    @Override
    public void onClick(View v) {
//      ItemDetail.this.setVisibility(GONE);
      ItemDetail.this.hide();
    }
  }

  class BuyClick implements OnClickListener {

    @Override
    public void onClick(View v) {
      if (ItemDetail.this.listingView != null) {
        ItemDetail.this.listingView.getItem().openMeliApp(v.getContext());
      }
    }
  }
}
