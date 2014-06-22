package com.cuantocuesta.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.ListingsActivity;
import com.google.common.base.Function;

public class ItemStackableView<T> extends RelativeLayout {

  private FrameLayout stack;
  private LikeableView current;
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
        ItemStackableView.this.dislike(listingsActivity);
      }

      @Override
      public void onSwipeRight() {
        ItemStackableView.this.like(listingsActivity);
      }
    });

    findViewById(R.id.lindo).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        like(listingsActivity);
      }
    });

    findViewById(R.id.feo).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        dislike(listingsActivity);
      }
    });

    findViewById(R.id.detail_button).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        showDetail();
      }
    });
  }

  public void dislike(ListingsActivity listingsActivity) {
    if (current != null) current.disliked();
    replaceNext(listingsActivity, Animacion.ANIMATE_LEFT);
  }

  public void like(final ListingsActivity listingsActivity) {
    if (current != null) current.liked();
    replaceNext(listingsActivity, Animacion.ANIMATE_RIGHT);
  }

  public void showDetail(){
    this.onShowDetail.apply(this);
  }

  protected void replaceNext(ListingsActivity listingsActivity, Animacion animacion) {
    if(stack.getChildCount() == 0) return;

    View lastChild = stack.getChildAt(stack.getChildCount() - 1);
    animacion.animate(lastChild);
    stack.removeView(lastChild);

    populateCurrent(listingsActivity);
  }

  private void populateCurrent(ListingsActivity listingsActivity) {
    current = listingsActivity.pop();
    if(current != null){
      stack.addView(current.getImageViewAndRemoveFromParent(), 0);
    }
  }

  public void populateIfEmpty(ListingsActivity listingsActivity) {
    if(current == null){
      populateCurrent(listingsActivity);
    }
  }

  public void setOnShowDetail(Function<ItemStackableView, ItemStackableView> onShowDetail) {
    this.onShowDetail = onShowDetail;
  }

  public LikeableView<T> getCurrent() {
    return current;
  }

  private enum Animacion {
    ANIMATE_RIGHT{
      public void animate(View view){
        if(view != null){
          TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
          slide(view, animate);
        }
      }
    },
    ANIMATE_LEFT{
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
