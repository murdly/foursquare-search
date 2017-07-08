package com.akarbowy.foursquaresearch.ui;

import com.akarbowy.foursquaresearch.di.ActivityScope;
import com.akarbowy.foursquaresearch.di.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SearchActivityModule.class)
public interface SearchActivityComponent {

    void inject(SearchActivity activity);

}
