
package com.domefavor.android.data.models.resources;

import android.util.Log;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/3/17.
 */

@AutoValue
public abstract class Location {

    @SerializedName("long")
    public abstract double longitude();

    @SerializedName("lat")
    public abstract double latitude();

    public static TypeAdapter<Location> typeAdapter(Gson gson) {
        return new AutoValue_Location.GsonTypeAdapter(gson);
    }

    public static Location create(double longitude, double latitude){
        return new AutoValue_Location(longitude, latitude);
    }
}
