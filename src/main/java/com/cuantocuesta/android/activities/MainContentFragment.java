package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuantocuesta.R;

public class MainContentFragment  extends Fragment {
  public static final String ARG_OS= "OS";
  private Integer numero;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_container, null);

    TextView textView = (TextView) view.findViewById(R.id.textView1);
    textView.setText(numero.toString());
    return view;
  }
  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }
  @Override
  public void setArguments(Bundle args) {
    numero = args.getInt(ARG_OS);
  }
}