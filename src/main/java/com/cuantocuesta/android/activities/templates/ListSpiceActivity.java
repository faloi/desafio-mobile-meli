package com.cuantocuesta.android.activities.templates;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.cuantocuesta.R;
import com.cuantocuesta.android.adapters.ModelToPictureAdapter;
import com.cuantocuesta.android.applicationModels.Displayable;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import roboguice.util.temp.Ln;

import java.util.List;

public abstract class ListSpiceActivity<TResponse, TService, TModel extends Displayable> extends SpiceActivity {
  protected RetrofitSpiceRequest<TResponse, TService> request;
  protected ListView listingsListView;
  protected View loadingView;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    Ln.getConfig().setLoggingLevel(Log.ERROR);
    listingsListView = (ListView) view.findViewById(R.id.listview_github);
    loadingView = findViewById(R.id.loading_layout);

    request = new RetrofitSpiceRequest<TResponse, TService>(getResponseClass(), getServiceClass()) {
      @Override
      public TResponse loadDataFromNetwork() throws Exception {
        return performQuery(getService());
      }
    };
  }

  @Override
  protected int layoutId() {
    return R.layout.activity_main;
  }

  @Override
  public void onStart() {
    super.onStart();
    loadItems();
  }

  protected abstract Class<TResponse> getResponseClass();
  protected abstract Class<TService> getServiceClass();
  protected abstract TResponse performQuery(TService service);
  protected abstract List<TModel> getResultsFromResponse(TResponse result);
  protected abstract SpiceListItemView<TModel> createView(Context context);

  private ListAdapter getAdapter(List<TModel> items) {
    return new ModelToPictureAdapter<TModel>(ListSpiceActivity.this.getActivity(), getSpiceManagerBinary(), items) {
      @Override
      protected SpiceListItemView<TModel> getView(Context context) {
        return ListSpiceActivity.this.createView(context);
      }

      @Override
      protected String getId(TModel model) {
        return model.getId();
      }

      @Override
      protected String getPictureUrl(TModel model) {
        return model.getThumbnail();
      }
    };
  }

  private void updateListViewContent(List<TModel> items) {
    listingsListView.setAdapter(getAdapter(items));

    loadingView.setVisibility(View.GONE);
    listingsListView.setVisibility(View.VISIBLE);
  }

  private void loadItems() {
    ListSpiceActivity.this.getActivity().setProgressBarIndeterminateVisibility(true);
    getSpiceManager().execute(request, "meli", DurationInMillis.ONE_MINUTE, new ItemsRequestListener());
  }

  public final class ItemsRequestListener implements RequestListener<TResponse> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {
      ListSpiceActivity.this.getActivity().setProgressBarIndeterminateVisibility(false);
      Toast.makeText(ListSpiceActivity.this.getActivity(), "Ha ocurrido un error al cargar los datos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(TResponse result) {
      ListSpiceActivity.this.getActivity().setProgressBarIndeterminateVisibility(false);
      updateListViewContent(getResultsFromResponse(result));
    }
  }
}
