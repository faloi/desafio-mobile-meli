package com.cuantocuesta.android;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import com.cuantocuesta.R;
import com.cuantocuesta.android.activities.SpiceActivity;
import com.cuantocuesta.android.adapters.ListingToPictureAdapter;
import com.cuantocuesta.android.services.requests.MeliRequest;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.cuantocuesta.domain.meli.dtos.Listing;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import roboguice.util.temp.Ln;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends SpiceActivity {

  // ============================================================================================
  // ATTRIBUTES
  // ============================================================================================

  private MeliRequest meliRequest;
  private String query = "campera de cuero";

  private ListView listingsListView;
  private View loadingView;

  private ListingToPictureAdapter listingToPictureAdapter;

  // ============================================================================================
  // ACTIVITY LIFE CYCLE
  // ============================================================================================

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Ln.getConfig().setLoggingLevel(Log.ERROR);
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setProgressBarIndeterminateVisibility(false);
    setContentView(R.layout.activity_main);

    listingsListView = (ListView) findViewById(R.id.listview_github);
    loadingView = findViewById(R.id.loading_layout);

    meliRequest = new MeliRequest(query);
  }

  @Override
  protected void onStart() {
    super.onStart();
    loadListings();
  }

  // ============================================================================================
  // PRIVATE METHODS
  // ============================================================================================

  private void updateListViewContent(List<Listing> items) {
    listingToPictureAdapter = new ListingToPictureAdapter(this, getSpiceManagerBinary(), items);
    listingsListView.setAdapter(listingToPictureAdapter);

    loadingView.setVisibility(View.GONE);
    listingsListView.setVisibility(View.VISIBLE);
  }

  private void loadListings() {
    setProgressBarIndeterminateVisibility(true);
    getSpiceManager().execute(meliRequest, "meli", DurationInMillis.ONE_MINUTE, new ItemsRequestListener());
  }

  // ============================================================================================
  // INNER CLASSES
  // ============================================================================================

  public final class ItemsRequestListener implements RequestListener<Example> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {
      setProgressBarIndeterminateVisibility(false);
      Toast.makeText(MainActivity.this, "Ha ocurrido un error al cargar las publicaciones", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(Example result) {
      setProgressBarIndeterminateVisibility(false);
      updateListViewContent(result.getListings());
    }
  }
}
