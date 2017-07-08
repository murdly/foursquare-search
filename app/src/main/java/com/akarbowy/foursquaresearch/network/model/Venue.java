package com.akarbowy.foursquaresearch.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("id") @Expose
    public String id;

    @SerializedName("name") @Expose
    public String name;
}
