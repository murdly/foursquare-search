package com.akarbowy.foursquaresearch.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupItem {

    @SerializedName("venue") @Expose
    public Venue venue;
}
