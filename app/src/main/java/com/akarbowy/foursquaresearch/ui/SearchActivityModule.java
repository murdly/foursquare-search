package com.akarbowy.foursquaresearch.ui;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    private SearchContract.View view;

    public SearchActivityModule(SearchContract.View view) {
        this.view = view;
    }

    @Provides
    SearchContract.View view(){
        return view;
    }
}
