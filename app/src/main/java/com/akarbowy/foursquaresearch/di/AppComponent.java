package com.akarbowy.foursquaresearch.di;

import com.akarbowy.foursquaresearch.network.FoursquareService;
import com.akarbowy.foursquaresearch.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface AppComponent {

    FoursquareService service();
}
