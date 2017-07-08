package com.akarbowy.foursquaresearch.data;


import com.akarbowy.foursquaresearch.data.model.VenueItem;

import java.util.List;

public interface DataSource {

    void loadVenues(String byName, GetVenuesCallback callback);

    interface GetVenuesCallback {

        void onDataLoaded(List<VenueItem> data);

        void onDataNotAvailable();

    }
}
