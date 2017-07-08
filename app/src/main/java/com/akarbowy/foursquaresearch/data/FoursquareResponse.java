package com.akarbowy.foursquaresearch.data;


import com.akarbowy.foursquaresearch.data.model.Meta;
import com.akarbowy.foursquaresearch.data.model.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoursquareResponse {

    @SerializedName("meta") @Expose
    public Meta meta;

    @SerializedName("response") @Expose
    public Response response;
}

