package com.cuantocuesta.android;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.SpiceActivity;
import com.cuantocuesta.android.services.requests.MeliRequest;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Result;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

public class MainActivity extends SpiceActivity {

  // ============================================================================================
  // ATTRIBUTES
  // ============================================================================================

  private TextView mTextView;
  private MeliRequest meliRequest;
  private String query = "campera de cuero";

  // ============================================================================================
  // ACTIVITY LIFE CYCLE
  // ============================================================================================

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_PROGRESS);
    setContentView(R.layout.activity_main);

    mTextView = (TextView) findViewById(R.id.textview_lorem_ipsum);

    meliRequest = new MeliRequest(query);
  }

  @Override
  protected void onStart() {
    super.onStart();
    getSpiceManager().execute(meliRequest, "meli", DurationInMillis.ONE_MINUTE, new ItemsRequestListener());
  }

  // ============================================================================================
  // PRIVATE METHODS
  // ============================================================================================

  private void updateItems(final List<Result> results) {
    String originalText = getString(R.string.textview_text);

    StringBuilder builder = new StringBuilder();
    builder.append(originalText);
    builder.append(query + ":");
    builder.append('\n');
    builder.append('\n');
    for (Result result : results) {
      builder.append('\t');
      builder.append(result.getTitle());
      builder.append('\t');
      builder.append('(');
      builder.append(result.getPrice());
      builder.append(')');
      builder.append('\n');
    }
    mTextView.setText(builder.toString());
  }

  // ============================================================================================
  // INNER CLASSES
  // ============================================================================================

  public final class ItemsRequestListener implements RequestListener<Example> {

    @Override
    public void onRequestFailure(SpiceException spiceException) {
      Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(Example example) {
      Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
      updateItems(example.results);
    }
  }
}
