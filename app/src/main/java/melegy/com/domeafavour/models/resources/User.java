
package melegy.com.domeafavour.models.resources;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/3/17.
 */

@AutoValue
public abstract class User {

    @SerializedName("first_name")
    abstract String firstName();

    @SerializedName("last_name")
    abstract String lastName();

    @SerializedName("email")
    abstract String email();

    @Nullable
    @SerializedName("current_location")
    abstract Location currentLocation();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

}
