package com.cuantocuesta.android.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.CustomFragment;

public class GeneroActivity extends CustomFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View thisView = inflater.inflate(R.layout.genero_fragment, null);
    return thisView;
  }
}
