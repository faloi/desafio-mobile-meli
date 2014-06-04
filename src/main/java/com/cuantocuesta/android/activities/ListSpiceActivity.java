package com.cuantocuesta.android.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import com.cuantocuesta.R;
import com.cuantocuesta.android.services.Meli;
import com.cuantocuesta.domain.meli.dtos.Example;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import roboguice.util.temp.Ln;

public abstract class ListSpiceActivity<TModel, TService> extends SpiceActivity {
  protected RetrofitSpiceRequest<TModel, TService> request;

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

    request = new RetrofitSpiceRequest<TModel, TService>(getModelClass(), getServiceClass()) {
      @Override
      public TModel loadDataFromNetwork() throws Exception {
        return performQuery(getService());
      }
    };
  }

  protected abstract Class<TModel> getModelClass();
  protected abstract Class<TService> getServiceClass();
  protected abstract TModel performQuery(TService service);
}
