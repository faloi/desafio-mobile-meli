package com.cuantocuesta.android.activities.templates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuantocuesta.android.services.MeliService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

public abstract class SpiceActivity extends CustomFragment {
  private SpiceManager spiceManager = new SpiceManager(MeliService.class);
  private OkHttpBitmapSpiceManager spiceManagerBinary = new OkHttpBitmapSpiceManager();
  private View thisView;

  @Override
  public void onStart() {
    spiceManager.start(this.getActivity());
    spiceManagerBinary.start(this.getActivity());
    super.onStart();
  }

  @Override
  public void onStop() {
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

  public View findViewById(int id){
    return thisView.findViewById(id);
  }

  protected abstract int layoutId();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    thisView =  inflater.inflate(layoutId(), null);

    this.onCreateFrame(savedInstanceState, thisView);
    return thisView;
  }

  protected abstract void onCreateFrame(Bundle savedInstanceState, View view);
}