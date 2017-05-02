
package melegy.com.domeafavour.data.local;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by ahmad on 4/28/17.
 */

public class DatabaseContract {

    static final String CONTENT_AUTHORITY = "melegy.com.domeafavour";
    private static final String CONTENT_SCHEME = "content://";
    static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);

    static final String PATH_FAVOR = "favor";

    public DatabaseContract() {
    }

    public static abstract class Favor implements BaseColumns {
        @NonNull
        public static final String CONTENT_URI_STRING = "content://" + CONTENT_AUTHORITY + "/"
                + PATH_FAVOR;
        static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

        static final String CONTENT_USER_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY
                + "/" + PATH_FAVOR;
        static final String CONTENT_USER_ITEM_TYPE = "vnd.android.cursor.item/"
                + CONTENT_AUTHORITY + "/" + PATH_FAVOR;

        public static final String TABLE_NAME = "favor";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";

        static String getPostCreateQuery() {
            return "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " TEXT NOT NULL PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT NOT NULL" + ");";
        }

        static String getUserDeleteQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }

        static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
