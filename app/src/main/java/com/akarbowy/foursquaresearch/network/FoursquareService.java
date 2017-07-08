package com.akarbowy.foursquaresearch.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoursquareService {
    String BASE_URL = "https://api.foursquare.com/v2/";

    @GET("venues/explore")
    Call<ResponseBody> search(@Query("query") String query,
                              @Query("near") String near);
}
