package com.akarbowy.foursquaresearch.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Group {

    @SerializedName("items") @Expose
    public List<VenueItem> items;
}
