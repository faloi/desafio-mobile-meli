package com.cuantocuesta.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cuantocuesta.R;

import java.text.NumberFormat;

public class ItemDetail extends RelativeLayout{
  private final ImageView listingImage;
  private final TextView price;
  private ListingView listingView;

  public ItemDetail(Context context, AttributeSet attrs) {
    super( context, attrs);
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.listing_detail, this, true);
    listingImage = (ImageView)findViewById(R.id.listing_detail_main_image);
    price = (TextView)findViewById(R.id.listing_detail_price);

    findViewById(R.id.listing_detail_button_carrito).setOnClickListener(new BuyClick());
    findViewById(R.id.listing_detail_button_back).setOnClickListener(new BackClick());
  }

  public void update(ListingView listing){
    listingView = listing;

    ImageView view = listing.getImageView(0);
    listingImage.setImageDrawable(view.getDrawable());

    price.setText(NumberFormat.getCurrencyInstance().format(listing.getListing().getPrice()));

    this.setAnimation(AnimationUtils.loadAnimation(this.getContext(), R.anim.go_up));

    ItemDetail.this.setVisibility(VISIBLE);
    this.animate();

  }

  public void hide(){
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

  class BackClick implements OnClickListener{

    @Override
    public void onClick(View v) {
//      ItemDetail.this.setVisibility(GONE);
      ItemDetail.this.hide();
    }
  }

  class BuyClick implements OnClickListener{

    @Override
    public void onClick(View v) {
      if(ItemDetail.this.listingView != null){
        ItemDetail.this.listingView.openMeliApp();
      }
    }
  }
}