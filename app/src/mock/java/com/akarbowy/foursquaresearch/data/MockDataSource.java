package com.akarbowy.foursquaresearch.data;


import java.util.ArrayList;

import javax.inject.Singleton;

@Singleton
public class MockDataSource implements DataSource {
    @Override public void loadVenues(String byName, GetVenuesCallback callback) {
        callback.onDataLoaded(new ArrayList());
    }
}
