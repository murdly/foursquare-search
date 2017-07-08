package com.akarbowy.foursquaresearch.ui;


import com.akarbowy.foursquaresearch.data.DataSource;
import com.akarbowy.foursquaresearch.data.model.VenueItem;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    private DataSource dataSource;

    @Inject
    public SearchPresenter(SearchContract.View view, DataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    @Override
    public void search(String query) {
        view.setLoading(true);

        dataSource.loadVenues(query, new DataSource.GetVenuesCallback() {
            @Override public void onDataLoaded(List<VenueItem> data) {
                if (!view.isActive()){
                    return;
                }
                view.setLoading(false);

                view.setVenues(data);
                view.setEmptyState(data.isEmpty());
            }

            @Override public void onDataNotAvailable() {
                if (!view.isActive()){
                    return;
                }

                view.setLoading(false);
                view.showError();
            }
        });
    }

}
