
package com.domefavor.android.data.models.requests;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/15/17.
 */

@AutoValue
public abstract class SignUpRequest {

    @SerializedName("first_name")
    public abstract String firstName();

    @SerializedName("last_name")
    public abstract String lastName();

    @SerializedName("email")
    public abstract String email();

    @SerializedName("avatar")
    public abstract String avatar();

    public static TypeAdapter<SignUpRequest> typeAdapter(Gson gson) {
        return new AutoValue_SignUpRequest.GsonTypeAdapter(gson);
    }

    public static SignUpRequest create(String first_name, String last_name, String email,
            String avatar) {
        return new AutoValue_SignUpRequest(first_name, last_name, email, avatar);
    }

}
