package com.cuantocuesta.android.activities.templates;

import android.support.v4.app.Fragment;

import com.cuantocuesta.android.activities.CanHandleBackButton;

public abstract class CustomFragment extends Fragment implements CanHandleBackButton{

  @Override
  public boolean onBackPressed() {
    return false;
  }
}
