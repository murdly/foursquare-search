package com.akarbowy.foursquaresearch.ui;


public interface SearchContract {

    interface View {
    }

    interface Presenter {
        void search(String query);
    }
}
