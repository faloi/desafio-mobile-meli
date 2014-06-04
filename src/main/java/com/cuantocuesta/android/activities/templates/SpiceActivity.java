package com.cuantocuesta.android.activities.templates;

import android.app.Activity;

import com.cuantocuesta.android.services.MeliService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

public abstract class SpiceActivity extends Activity {
  private SpiceManager spiceManager = new SpiceManager(MeliService.class);
  private OkHttpBitmapSpiceManager spiceManagerBinary = new OkHttpBitmapSpiceManager();

  @Override
  protected void onStart() {
    spiceManager.start(this);
    spiceManagerBinary.start(this);
    super.onStart();
  }

  @Override
  protected void onStop() {
    spiceManager.shouldStop();
    spiceManagerBinary.shouldStop();
    super.onStop();
  }

  protected SpiceManager getSpiceManager() {
    return spiceManager;
  }
  protected OkHttpBitmapSpiceManager getSpiceManagerBinary() {
    return spiceManagerBinary;
  }

}