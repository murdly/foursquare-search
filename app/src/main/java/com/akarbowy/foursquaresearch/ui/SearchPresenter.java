package com.akarbowy.foursquaresearch.ui;


import android.util.Log;

import com.akarbowy.foursquaresearch.network.FoursquareResponse;
import com.akarbowy.foursquaresearch.network.FoursquareService;
import com.akarbowy.foursquaresearch.network.model.Group;
import com.akarbowy.foursquaresearch.network.model.GroupItem;

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
        service.search(query, location)
                .enqueue(new Callback<FoursquareResponse>() {
                    @Override public void onResponse(Call<FoursquareResponse> call, Response<FoursquareResponse> response) {
                        if (response.isSuccessful()) {
                            List<Group> group = response.body().response.groups;
                            List<GroupItem> item = group.get(0).items;
                            Log.d("FQ", "onResponse" + item.get(0).venue.name);
                        } else {
                            Log.d("FQ", "onResponse fail");
                        }
                    }

                    @Override public void onFailure(Call<FoursquareResponse> call, Throwable t) {
                        Log.d("FQ", "onFailure" + t.getMessage());
                    }
                });
    }
}
