package com.cuantocuesta.android.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.templates.CustomFragment;
import com.cuantocuesta.storage.DatabaseHandler;
import com.google.common.base.Joiner;

public class GeneroActivity extends CustomFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View thisView = inflater.inflate(R.layout.genero_fragment, null);
    final DatabaseHandler db = new DatabaseHandler(this.getActivity());
    thisView.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText input = (EditText) thisView.findViewById(R.id.database);
        db.addLikedItem(input.getText().toString());

        TextView output = (TextView) thisView.findViewById(R.id.result);

        Joiner joiner = Joiner.on(" ").skipNulls();
        output.setText(joiner.join(db.getAllLikedItems()));
      }
    });
    return thisView;
  }
}
