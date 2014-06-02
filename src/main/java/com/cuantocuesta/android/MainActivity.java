package com.cuantocuesta.android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import com.cuantocuesta.R;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
  }
}