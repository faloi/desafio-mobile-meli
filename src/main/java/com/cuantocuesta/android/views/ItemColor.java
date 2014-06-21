package com.cuantocuesta.android.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.cuantocuesta.domain.meli.CombinatedColor;

public class ItemColor extends View {
  public ItemColor(Context context) {
    super(context);
    this.build(context);
  }

  public ItemColor(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.build(context);
  }

  private void build(Context context) {
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
    params.setMargins(5,5,5,5);
    this.setLayoutParams(params);
  }

  public ItemColor color(CombinatedColor color) {
    this.setBackgroundColor(Color.parseColor(color.getPrimary()));
    return this;
  }
}
