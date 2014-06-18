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

public class ItemStackableView extends RelativeLayout {

  private FrameLayout stack;
  private ListingView current;
  private ListingView next;

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
  }

  public void dislike(View v, ListingsActivity listingsActivity) {
    if (current != null) current.disliked();
    View lastChild = stack.getChildAt(stack.getChildCount() - 1);
    slideToLeft(lastChild);
    stack.removeView(lastChild);
    replaceNext(v, listingsActivity);
  }

  public void like(final View v, final ListingsActivity listingsActivity) {
    if (current != null) current.liked();
    View lastChild = stack.getChildAt(stack.getChildCount() - 1);
    slideToRight(lastChild);
    stack.removeView(lastChild);
    replaceNext(v, listingsActivity);
  }

  protected void replaceNext(View v, ListingsActivity listingsActivity) {
    ListAdapter adapter = listingsActivity.getListingsListView().getAdapter();
    if(!adapter.isEmpty()){
      current = next;

      if(adapter.getCount() > 1){
        next = new ListingView(v.getContext(), listingsActivity.getListingsStream(), listingsActivity);
        stack.addView(getListingView(1, next, adapter), 0);
      }
    }
  }

  public void slideToLeft(View view){
    if(view != null){
      TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
      slide(view, animate);
    }
  }

  public void slideToRight(View view){
    if (view != null) {
      TranslateAnimation animate = new TranslateAnimation(0,-view.getWidth(),0,0);
      slide(view, animate);
    }
  }

  public void slide(View view, TranslateAnimation animate) {
    animate.setDuration(400);
    animate.setFillAfter(true);
    view.startAnimation(animate);
    view.setVisibility(View.GONE);
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

    if(next == null  && listingsActivity.getListingsListView().getAdapter().getCount()>1){
      next = new ListingView(listingsActivity.getActivity(), listingsActivity.getListingsStream(), listingsActivity);
      stack.addView(getListingView(1, next, listingsActivity.getListingsListView().getAdapter()));
    }

  }
}
