package com.akarbowy.foursquaresearch.ui;


import com.akarbowy.foursquaresearch.network.FoursquareResponse;
import com.akarbowy.foursquaresearch.network.FoursquareService;
import com.akarbowy.foursquaresearch.network.model.VenueItem;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    private FoursquareService service;

    private String location = "london gb";

    @Inject
    public SearchPresenter(SearchContract.View view, FoursquareService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void search(String query) {
        view.setLoading(true);

        service.search(query, location)
                .enqueue(new Callback<FoursquareResponse>() {
                    @Override public void onResponse(Call<FoursquareResponse> call, Response<FoursquareResponse> response) {
                        if (!view.isActive()){
                            return;
                        }

                        view.setLoading(false);

                        if (response.isSuccessful()) {
                            handleResponse(response.body());
                        } else {
                            view.showError();
                        }
                    }

                    @Override public void onFailure(Call<FoursquareResponse> call, Throwable t) {
                        if (!view.isActive()){
                            return;
                        }

                        view.setLoading(false);
                        view.showError();
                    }
                });
    }

    private void handleResponse(FoursquareResponse body){
        if(body == null) {
            return;
        }

        List<VenueItem> venues = body.response.getVenues();

        view.setVenues(venues);
        view.setEmptyState(venues.isEmpty());
    }
}
