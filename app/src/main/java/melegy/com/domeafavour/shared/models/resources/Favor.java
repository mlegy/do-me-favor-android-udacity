
package melegy.com.domeafavour.shared.models.resources;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmad on 4/3/17.
 */

@AutoValue
public abstract class Favor {

    @SerializedName("title")
    abstract String title();

    @SerializedName("description")
    abstract String description();

    @SerializedName("owner")
    abstract User owner();

    @SerializedName("benefactor")
    abstract User benefactor();

    @SerializedName("location")
    abstract Location location();

    @SerializedName("is_done")
    abstract Boolean done();

    public static TypeAdapter<Favor> typeAdapter(Gson gson) {
        return new AutoValue_Favor.GsonTypeAdapter(gson);
    }

    public static Favor create(String title, String description, User owner, User benefactor,
            Location location, Boolean done) {
        return new AutoValue_Favor(title, description, owner, benefactor, location, done);
    }

}
