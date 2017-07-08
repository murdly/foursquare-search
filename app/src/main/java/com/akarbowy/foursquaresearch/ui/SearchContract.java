package com.akarbowy.foursquaresearch.ui;


import com.akarbowy.foursquaresearch.network.model.VenueItem;

import java.util.List;

public interface SearchContract {

    interface View {
        void setVenues(List<VenueItem> venues);
    }

    interface Presenter {
        void search(String query);
    }
}
