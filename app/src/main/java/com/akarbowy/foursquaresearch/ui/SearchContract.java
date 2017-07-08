package com.akarbowy.foursquaresearch.ui;


import com.akarbowy.foursquaresearch.data.model.VenueItem;

import java.util.List;

public interface SearchContract {

    interface View {
        boolean isActive();

        void setVenues(List<VenueItem> venues);

        void setLoading(boolean isLoading);

        void setEmptyState(boolean visible);

        void showError();
    }

    interface Presenter {
        void search(String query);
    }
}
