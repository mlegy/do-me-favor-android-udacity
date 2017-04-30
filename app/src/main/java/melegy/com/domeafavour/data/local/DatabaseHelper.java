package melegy.com.domeafavour.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import melegy.com.domeafavour.BuildConfig;

/**
 * Created by ahmad on 4/28/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = BuildConfig.DB_NAME;
    private static final int DATABASE_VERSION = BuildConfig.DB_VERSION;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.Favor.getPostCreateQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseContract.Favor.getUserDeleteQuery());
        onCreate(sqLiteDatabase);
    }
}

