package com.akarbowy.foursquaresearch.data;


import javax.inject.Singleton;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Singleton
    @Provides
    DataSource dataSource(Lazy<FoursquareService> service){
        return new FoursquareDataSource(service);
    }
}
