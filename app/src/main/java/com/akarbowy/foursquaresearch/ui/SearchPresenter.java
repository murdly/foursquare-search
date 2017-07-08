package com.akarbowy.foursquaresearch.ui;


import com.akarbowy.foursquaresearch.network.FoursquareService;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    private FoursquareService service;

    @Inject
    public SearchPresenter(SearchContract.View view, FoursquareService service) {
        this.view = view;
        this.service = service;
    }
}
