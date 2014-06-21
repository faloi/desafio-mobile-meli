package com.cuantocuesta.android.views;

import android.widget.ImageView;

public interface LikeableView<T> {
  public void disliked();

  public void liked();

  public ImageView getImageViewAndRemoveFromParent();

  public ImageView getImageView();

  public T getItem();
}
