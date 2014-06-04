package com.cuantocuesta.android.activities;

import android.app.Activity;

import com.cuantocuesta.android.services.MeliService;
import com.octo.android.robospice.SpiceManager;

public abstract class SpiceActivity extends Activity {
  private SpiceManager spiceManager = new SpiceManager(MeliService.class);

  @Override
  protected void onStart() {
    spiceManager.start(this);
    super.onStart();
  }

  @Override
  protected void onStop() {
    spiceManager.shouldStop();
    super.onStop();
  }

  protected SpiceManager getSpiceManager() {
    return spiceManager;
  }

}