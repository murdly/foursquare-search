package com.akarbowy.foursquaresearch.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Singleton
    @Provides
    DataSource dataSource(){
        return new MockDataSource();
    }
}
