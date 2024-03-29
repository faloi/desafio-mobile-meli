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
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.spicelist.SpiceListItemView;
import com.octo.android.robospice.spicelist.SpiceListView;

import roboguice.util.temp.Ln;

import java.util.List;

public abstract class ListSpiceActivity<TResponse, TService, TModel extends Displayable> extends SpiceActivity {
  protected RetrofitSpiceRequest<TResponse, TService> request;
  protected SpiceListView listingsListView;
  protected View loadingView;
  protected List<TModel> items;

  @Override
  public void onCreateFrame(Bundle savedInstanceState, View view) {
    Ln.getConfig().setLoggingLevel(Log.ERROR);
    listingsListView = (SpiceListView) view.findViewById(R.id.listview_github);
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

    public void removeItem(TModel item) {
        items.remove(item);
        updateListViewContent(items);
    }

    protected void updateListViewContent(List<TModel> items) {
        if (items.isEmpty()) {
            this.loadItems();
            return;
        }

        listingsListView.setAdapter(getAdapter(items));

        loadingView.setVisibility(View.GONE);
        listingsListView.setVisibility(View.VISIBLE);
    }

    protected void loadItems() {
        ListSpiceActivity.this.getActivity().setProgressBarIndeterminateVisibility(true);
        getSpiceManager().execute(request, new ItemsRequestListener());
    }

    public final class ItemsRequestListener implements RequestListener<TResponse> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            ListSpiceActivity.this.getActivity().setProgressBarIndeterminateVisibility(false);
            Toast.makeText(ListSpiceActivity.this.getActivity(), getString(R.string.no_more_listings_for_now), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(TResponse result) {
            ListSpiceActivity.this.getActivity().setProgressBarIndeterminateVisibility(false);
            items = getResultsFromResponse(result);
            updateListViewContent(items);
        }
    }

    public ListView getListingsListView() {
        return listingsListView;
    }
}
