
package com.domefavor.android.data.models.resources;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/3/17.
 */

@AutoValue
public abstract class User {

    @SerializedName("_id")
    public abstract String id();

    @SerializedName("first_name")
    public abstract String firstName();

    @SerializedName("last_name")
    public abstract String lastName();

    @SerializedName("email")
    public abstract String email();

    @SerializedName("avatar")
    public abstract String avatar();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

}
