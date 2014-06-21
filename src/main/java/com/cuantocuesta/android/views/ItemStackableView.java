package com.cuantocuesta.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.ListingsActivity;
import com.google.common.base.Function;

public class ItemStackableView extends RelativeLayout {

  private FrameLayout stack;
  private ListingView current;
  private Function<ItemStackableView,ItemStackableView> onShowDetail;

  public ItemStackableView(Context listingsActivity, AttributeSet attrs) {
    super(listingsActivity, attrs);
    LayoutInflater inflater = (LayoutInflater) listingsActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.stackable_view, this, true);
  }

  public void build(final ListingsActivity listingsActivity) {

    stack = (FrameLayout) findViewById(R.id.stack_id);
    this.setOnTouchListener(new OnSwipeTouchListener(listingsActivity.getActivity()) {
      @Override
      public void onSwipeLeft() {
        ItemStackableView.this.like(stack, listingsActivity);
      }

      @Override
      public void onSwipeRight() {
        ItemStackableView.this.dislike(stack, listingsActivity);
      }
    });

    findViewById(R.id.lindo).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        dislike(v, listingsActivity);
      }
    });

    findViewById(R.id.feo).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        like(v, listingsActivity);
      }
    });

    findViewById(R.id.detail_button).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        showDetail();
      }
    });
  }

  public void dislike(View v, ListingsActivity listingsActivity) {
    if (current != null) current.disliked();
    replaceNext(v, listingsActivity, Animacion.ANIMATE_LEFT);
  }

  public void like(final View v, final ListingsActivity listingsActivity) {
    if (current != null) current.liked();
    replaceNext(v, listingsActivity, Animacion.ANIMATE_RIGHT);
  }

  public void showDetail(){
    this.onShowDetail.apply(this);
  }

  protected void replaceNext(View v, ListingsActivity listingsActivity, Animacion animacion) {
    if(stack.getChildCount() == 0) return;

    ListAdapter adapter = listingsActivity.getListingsListView().getAdapter();

    View lastChild = stack.getChildAt(stack.getChildCount() - 1);
    animacion.animate(lastChild);
    stack.removeView(lastChild);

    if(!adapter.isEmpty()){
        current = new ListingView(v.getContext(), listingsActivity.getListingsStream(), listingsActivity);
        stack.addView(getListingView(0, current, adapter), 0);
    }
  }

  private ImageView getListingView(int pos, ListingView someView, ListAdapter adapter) {
    adapter.getView(pos, someView, null);
    ImageView imageView = someView.getImageView(0);
    ((ViewGroup)imageView.getParent()).removeView(imageView);
    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    return imageView;
  }


  public void populate(ListingsActivity listingsActivity) {
    if(current == null && listingsActivity.getListingsListView().getAdapter().getCount()>0){
      current = new ListingView(listingsActivity.getActivity(), listingsActivity.getListingsStream(), listingsActivity);
      stack.addView(getListingView(0, current, listingsActivity.getListingsListView().getAdapter()));
    }
  }

  public void setOnShowDetail(Function<ItemStackableView, ItemStackableView> onShowDetail) {
    this.onShowDetail = onShowDetail;
  }

  public ListingView getCurrent() {
    return current;
  }

  private enum Animacion {
    ANIMATE_LEFT{
      public void animate(View view){
        if(view != null){
          TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
          slide(view, animate);
        }
      }
    },
    ANIMATE_RIGHT{
      public void animate(View view){
        if (view != null) {
          TranslateAnimation animate = new TranslateAnimation(0,-view.getWidth(),0,0);
          slide(view, animate);
        }
      }
    };

    public abstract void animate(View view);

    public void slide(View view, TranslateAnimation animate) {
      animate.setDuration(400);
      animate.setFillAfter(true);
      view.startAnimation(animate);
      view.setVisibility(View.GONE);
    }
  }
}
