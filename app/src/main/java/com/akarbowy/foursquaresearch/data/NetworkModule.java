package com.akarbowy.foursquaresearch.data;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .build();

        return client;
    }

    @Provides FoursquareService service(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoursquareService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FoursquareService.class);
    }
}
