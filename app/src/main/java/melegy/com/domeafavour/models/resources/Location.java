
package melegy.com.domeafavour.models.resources;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmad on 4/3/17.
 */

@AutoValue
public abstract class Location implements Parcelable{

    @SerializedName("coordinates")
    abstract List<Long> coordinates();

    public static TypeAdapter<Location> typeAdapter(Gson gson) {
        return new $AutoValue_Location.GsonTypeAdapter(gson);
    }

    public static Location create(List<Long> coordinates) {
        return new AutoValue_Location(coordinates);
    }

}
