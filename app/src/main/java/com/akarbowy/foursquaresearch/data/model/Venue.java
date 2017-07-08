package com.akarbowy.foursquaresearch.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("id") @Expose
    public String id;

    @SerializedName("name") @Expose
    public String name;

    @SerializedName("rating") @Expose
    public String rating;

    @SerializedName("ratingColor") @Expose
    public String ratingColor;

    public boolean hasRating() {
        return rating != null && !rating.isEmpty()
                && ratingColor != null && !ratingColor.isEmpty();
    }

}
