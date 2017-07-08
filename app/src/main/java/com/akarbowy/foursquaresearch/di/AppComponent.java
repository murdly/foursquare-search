package com.akarbowy.foursquaresearch.di;

import com.akarbowy.foursquaresearch.data.DataModule;
import com.akarbowy.foursquaresearch.data.DataSource;
import com.akarbowy.foursquaresearch.data.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetworkModule.class,
        DataModule.class
})
public interface AppComponent {

    DataSource dataSource();

}
