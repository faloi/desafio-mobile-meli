package com.cuantocuesta.android;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class NiceText extends TextView {
    public NiceText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!this.isInEditMode()){
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aliquam/Aliquam.ttf"));
            this.setPaintFlags(this.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
    }
}
