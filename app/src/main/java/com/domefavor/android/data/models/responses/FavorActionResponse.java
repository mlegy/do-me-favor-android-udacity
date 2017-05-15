package com.domefavor.android.data.models.responses;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import com.domefavor.android.data.models.resources.Error;

/**
 * Created by ahmad on 4/17/17.
 */

@AutoValue
public abstract class FavorActionResponse {

    @Nullable
    @SerializedName("success")
    public abstract String success();

    @Nullable
    @SerializedName("error")
    public abstract Error error();

    public static TypeAdapter<FavorActionResponse> typeAdapter(Gson gson) {
        return new AutoValue_FavorActionResponse.GsonTypeAdapter(gson);
    }

}
