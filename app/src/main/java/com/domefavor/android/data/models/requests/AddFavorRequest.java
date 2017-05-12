package com.domefavor.android.data.models.requests;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import com.domefavor.android.data.models.resources.Location;

/**
 * Created by ahmad on 5/6/17.
 */

@AutoValue
public abstract class AddFavorRequest {

    @SerializedName("title")
    public abstract String title();

    @SerializedName("description")
    public abstract String description();

    @SerializedName("owner")
    public abstract String owner();

    @SerializedName("location")
    public abstract Location location();

    public static TypeAdapter<AddFavorRequest> typeAdapter(Gson gson) {
        return new AutoValue_AddFavorRequest.GsonTypeAdapter(gson);
    }

    public static AddFavorRequest create(String title, String description, String owner, Location location){
        return new AutoValue_AddFavorRequest(title, description, owner, location);
    }
}
