
package com.domefavor.android.data.models.responses;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import com.domefavor.android.data.models.resources.Error;
import com.domefavor.android.data.models.resources.User;

/**
 * Created by ahmad on 4/11/17.
 */

@AutoValue
public abstract class LoginResponse {

    @Nullable
    @SerializedName("user")
    abstract User user();

    @Nullable
    @SerializedName("error")
    abstract Error error();

    public static TypeAdapter<LoginResponse> typeAdapter(Gson gson) {
        return new AutoValue_LoginResponse.GsonTypeAdapter(gson);
    }

}