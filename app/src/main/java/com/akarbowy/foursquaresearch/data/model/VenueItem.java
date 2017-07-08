package com.akarbowy.foursquaresearch.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenueItem {

    @SerializedName("venue") @Expose
    public Venue venue;
}
