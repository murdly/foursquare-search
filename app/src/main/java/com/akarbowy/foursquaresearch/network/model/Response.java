package com.akarbowy.foursquaresearch.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("groups") @Expose
    public List<Group> groups;

    public List<VenueItem> getVenues() {
        if(groups == null || groups.isEmpty()){
            return new ArrayList<>();
        }

        return  groups.get(0).items;
    }
}
