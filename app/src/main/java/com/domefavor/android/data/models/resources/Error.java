package com.domefavor.android.data.models.resources;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/11/17.
 */

@AutoValue
public abstract class Error {

    @SerializedName("code")
    abstract int code();

    @SerializedName("message")
    abstract String message();

    public static TypeAdapter<Error> typeAdapter(Gson gson) {
        return new AutoValue_Error.GsonTypeAdapter(gson);
    }

}
