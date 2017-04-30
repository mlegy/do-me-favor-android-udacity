
package melegy.com.domeafavour.data.models.resources;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverCreator;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteCreator;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import melegy.com.domeafavour.data.local.DatabaseContract;

/**
 * Created by ahmad on 4/3/17.
 */

@AutoValue
@StorIOSQLiteType(table = DatabaseContract.Favor.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.Favor.CONTENT_URI_STRING)
public abstract class Favor {

    @SerializedName("id")
    @StorIOSQLiteColumn(name = DatabaseContract.Favor.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.Favor.COLUMN_ID, key = true)
    abstract String id();

    @SerializedName("title")
    @StorIOSQLiteColumn(name = DatabaseContract.Favor.COLUMN_TITLE)
    @StorIOContentResolverColumn(name = DatabaseContract.Favor.COLUMN_TITLE)
    abstract String title();

    @SerializedName("description")
    @StorIOSQLiteColumn(name = DatabaseContract.Favor.COLUMN_DESCRIPTION)
    @StorIOContentResolverColumn(name = DatabaseContract.Favor.COLUMN_DESCRIPTION)
    abstract String description();

    @SerializedName("owner")
    @Nullable
    abstract User owner();

    @SerializedName("benefactor")
    @Nullable
    abstract User benefactor();

    @SerializedName("location")
    @Nullable
    abstract Location location();

    @SerializedName("is_done")
    @Nullable
    abstract Boolean done();

    public static TypeAdapter<Favor> typeAdapter(Gson gson) {
        return new AutoValue_Favor.GsonTypeAdapter(gson);
    }

    @StorIOSQLiteCreator
    @StorIOContentResolverCreator
    static Favor create(String id, String title, String description) {
        return new AutoValue_Favor(id, title, description, null, null, null, null);
    }
}
