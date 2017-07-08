package com.akarbowy.foursquaresearch.network;


import com.akarbowy.foursquaresearch.network.model.Meta;
import com.akarbowy.foursquaresearch.network.model.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoursquareResponse {

    @SerializedName("meta") @Expose
    public Meta meta;

    @SerializedName("response") @Expose
    public Response response;
}

