
package com.domefavor.android.features.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.domefavor.android.R;
import com.domefavor.android.data.local.DatabaseContract;
import com.domefavor.android.features.favors.updateFavor.FavorDetails;

import static com.domefavor.android.features.favors.updateFavor.FavorDetails.FAVOR_DISTANCE_KEY;
import static com.domefavor.android.features.favors.updateFavor.FavorDetails.FAVOR_ID_KEY;

/**
 * Created by ahmad on 5/16/17.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WidgetRemoteViewsService extends RemoteViewsService {

    private static final String[] FAVORS_COLUMNS = {
            DatabaseContract.Favor.TABLE_NAME + "." + DatabaseContract.Favor._ID,
            DatabaseContract.Favor.COLUMN_ID,
            DatabaseContract.Favor.COLUMN_TITLE,
            DatabaseContract.Favor.COLUMN_DISTANCE
    };

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewFactory();
    }

    public class ListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private Cursor data = null;

        @Override
        public void onCreate() {

        }

        @Override
        public void onDestroy() {
            if (data != null) {
                data.close();
                data = null;
            }
        }

        @Override
        public void onDataSetChanged() {
            if (data != null)
                data.close();

            final long identityToken = Binder.clearCallingIdentity();
            data = getContentResolver().query(DatabaseContract.Favor.CONTENT_URI,
                    FAVORS_COLUMNS,
                    null,
                    null,
                    null);
            Binder.restoreCallingIdentity(identityToken);
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (position == AdapterView.INVALID_POSITION || data == null
                    || !data.moveToPosition(position)) {
                return null;
            }

            RemoteViews remoteViews = new RemoteViews(getPackageName(),
                    R.layout.list_item_favor);

            String id = data.getString(1);
            String title = data.getString(2);
            float distance = data.getFloat(3);

            remoteViews.setTextViewText(R.id.text_favor_title, title);
            remoteViews.setTextViewText(R.id.text_favor_distance, formatDistance(distance));

            Intent detailsIntent = new Intent(getApplicationContext(), FavorDetails.class);
            detailsIntent.putExtra(FAVOR_ID_KEY, id);
            detailsIntent.putExtra(FAVOR_DISTANCE_KEY, distance);
            remoteViews.setOnClickFillInIntent(R.id.list_item, detailsIntent);

            return remoteViews;

        }

        private String formatDistance(float distance) {
            if (distance <= 10)
                return getString(R.string.just_her);
            else if (distance < 1000)
                return getString(R.string.meters_away,
                        Math.round(distance));
            else
                return getString(R.string.km_away,
                        Math.round(Math.round(distance / 1000)));
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return data.moveToPosition(i) ? data.getLong(0) : i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
