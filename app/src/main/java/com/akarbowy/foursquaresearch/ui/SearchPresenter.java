package com.akarbowy.foursquaresearch.ui;


import android.util.Log;

import com.akarbowy.foursquaresearch.network.FoursquareResponse;
import com.akarbowy.foursquaresearch.network.FoursquareService;
import com.akarbowy.foursquaresearch.network.model.Group;
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
                        view.setLoading(false);

                        if (response.isSuccessful()) {
                            List<Group> group = response.body().response.groups;
                            List<VenueItem> venues = group.get(0).items;

                            view.setLoading(false);
                            view.setVenues(venues);
                            Log.d("FQ", "onResponse");
                        } else {
                            Log.d("FQ", "onResponse fail");
                        }
                    }

                    @Override public void onFailure(Call<FoursquareResponse> call, Throwable t) {
                        view.setLoading(false);

                        Log.d("FQ", "onFailure" + t.getMessage());
                    }
                });
    }
}
