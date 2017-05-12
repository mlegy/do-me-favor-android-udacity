
package com.domefavor.android.data.models.resources;

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
    abstract double x();

    @SerializedName("lat")
    abstract double y();

    public static TypeAdapter<Location> typeAdapter(Gson gson) {
        return new AutoValue_Location.GsonTypeAdapter(gson);
    }

    public static Location create(double x, double y){
        return new AutoValue_Location(x, y);
    }
}
