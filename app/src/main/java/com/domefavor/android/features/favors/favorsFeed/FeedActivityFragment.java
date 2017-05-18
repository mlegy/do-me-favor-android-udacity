
package com.domefavor.android.features.favors.favorsFeed;

import android.Manifest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.domefavor.android.App;
import com.domefavor.android.R;
import com.domefavor.android.data.local.DatabaseContract;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedActivityFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.list_view)
    ListView listView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private GoogleApiClient mGoogleApiClient;

    @Inject
    FavorsFeedVM favorsFeedVM;

    static final int COL_FAVOR_ID = 1;
    static final int COL_FAVOR_TITLE = 2;
    static final int COL_FAVOR_DESC = 3;
    static final int COL_FAVOR_DISTANCE = 4;

    private static final String[] FAVORS_COLUMNS = {
            DatabaseContract.Favor.TABLE_NAME + "." + DatabaseContract.Favor._ID,
            DatabaseContract.Favor.COLUMN_ID,
            DatabaseContract.Favor.COLUMN_TITLE,
            DatabaseContract.Favor.COLUMN_DESCRIPTION,
            DatabaseContract.Favor.COLUMN_DISTANCE
    };
    private FavorsAdapter mAdapter;
    private Location lastLocation;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    public FeedActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getApp().getVMComponent().inject(this);
        initGoogleApiClient();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        mAdapter = new FavorsAdapter(this.getActivity(), null, 0);
        ButterKnife.bind(this, view);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
            if (cursor != null) {
                String favorId = cursor.getString(COL_FAVOR_ID);
                float distance = cursor.getFloat(COL_FAVOR_DISTANCE);
                ((Callback) getActivity())
                        .onItemSelected(favorId, distance);
            }
            mPosition = position;
        });
        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return view;
    }

    @Override
    public void onResume() {
        getLoaderManager().initLoader(0, null, this);
        super.onResume();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        RxPermissions rxPermissions = new RxPermissions(this.getActivity());
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (lastLocation != null)
                            getFavors(lastLocation);
                    }
                });
    }

    private void getFavors(Location location) {
        favorsFeedVM.getNearbyFavors(location.getLongitude(),
                location.getLatitude())
                .subscribe(favors -> updateUI(),
                        throwable -> Log.i("ERROR", throwable.getLocalizedMessage()));
    }

    private void updateUI() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void initGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri FavorsUri = DatabaseContract.Favor.CONTENT_URI;

        return new CursorLoader(getActivity(),
                FavorsUri,
                FAVORS_COLUMNS,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            listView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        void onItemSelected(String favorId, float distance);
    }
}
