package melegy.com.domeafavour.data.models.responses;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import melegy.com.domeafavour.data.models.resources.Error;
import melegy.com.domeafavour.data.models.resources.Favor;

/**
 * Created by ahmad on 4/17/17.
 */

@AutoValue
public abstract class AddFavorResponse {

    @Nullable
    @SerializedName("favor")
    abstract Favor favor();

    @Nullable
    @SerializedName("error")
    abstract Error error();

    public static TypeAdapter<AddFavorResponse> typeAdapter(Gson gson) {
        return new AutoValue_AddFavorResponse.GsonTypeAdapter(gson);
    }

}
