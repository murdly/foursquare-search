package com.akarbowy.foursquaresearch.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("groups") @Expose
    public List<Group> groups;
}
