package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.cuantocuesta.R;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import roboguice.util.temp.Ln;

import java.util.List;

public abstract class ListSpiceActivity<TResponse, TService, TModel> extends SpiceActivity {
  protected RetrofitSpiceRequest<TResponse, TService> request;
  protected ListView listingsListView;
  protected View loadingView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Ln.getConfig().setLoggingLevel(Log.ERROR);
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setProgressBarIndeterminateVisibility(false);
    setContentView(R.layout.activity_main);

    listingsListView = (ListView) findViewById(R.id.listview_github);
    loadingView = findViewById(R.id.loading_layout);

    request = new RetrofitSpiceRequest<TResponse, TService>(getResponseClass(), getServiceClass()) {
      @Override
      public TResponse loadDataFromNetwork() throws Exception {
        return performQuery(getService());
      }
    };
  }

  @Override
  protected void onStart() {
    super.onStart();
    loadItems();
  }

  protected abstract Class<TResponse> getResponseClass();
  protected abstract Class<TService> getServiceClass();
  protected abstract TResponse performQuery(TService service);
  protected abstract ListAdapter getAdapter(List<TModel> items);
  protected abstract List<TModel> getResultsFromResponse(TResponse result);

  private void updateListViewContent(List<TModel> items) {
    listingsListView.setAdapter(getAdapter(items));

    loadingView.setVisibility(View.GONE);
    listingsListView.setVisibility(View.VISIBLE);
  }

  private void loadItems() {
    setProgressBarIndeterminateVisibility(true);
    getSpiceManager().execute(request, "meli", DurationInMillis.ONE_MINUTE, new ItemsRequestListener());
  }

  public final class ItemsRequestListener implements RequestListener<TResponse> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {
      setProgressBarIndeterminateVisibility(false);
      Toast.makeText(ListSpiceActivity.this, "Ha ocurrido un error al cargar los datos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(TResponse result) {
      setProgressBarIndeterminateVisibility(false);
      updateListViewContent(getResultsFromResponse(result));
    }
  }
}
