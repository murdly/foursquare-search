package com.akarbowy.foursquaresearch.data;


import com.akarbowy.foursquaresearch.data.model.VenueItem;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoursquareDataSource implements DataSource {
    private String location = "london gb";

    private final Lazy<FoursquareService> service;

    @Inject
    public FoursquareDataSource(Lazy<FoursquareService> service) {
        this.service = service;
    }

    @Override public void loadVenues(String byName, final GetVenuesCallback callback) {
        service.get().search(byName, location).enqueue(new Callback<FoursquareResponse>() {
            @Override public void onResponse(Call<FoursquareResponse> call, Response<FoursquareResponse> response) {
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable();
                    return;
                }

                if(response.body() == null) {
                    callback.onDataNotAvailable();
                    return;
                }

                List<VenueItem> venues = response.body().response.getVenues();
                callback.onDataLoaded(venues);
            }

            @Override public void onFailure(Call<FoursquareResponse> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

}
