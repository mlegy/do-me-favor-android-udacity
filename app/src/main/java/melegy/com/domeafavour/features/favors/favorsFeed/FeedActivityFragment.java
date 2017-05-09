
package melegy.com.domeafavour.features.favors.favorsFeed;

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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import melegy.com.domeafavour.App;
import melegy.com.domeafavour.R;
import melegy.com.domeafavour.data.local.DatabaseContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedActivityFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.list_view)
    ListView listView;

    private GoogleApiClient mGoogleApiClient;

    @Inject
    FavorsFeedVM favorsFeedVM;

    static final int COL_FAVOR_TITLE = 1;
    static final int COL_FAVOR_DESC = 2;
    static final int COL_FAVOR_DISTANCE = 3;

    private static final String[] FAVORS_COLUMNS = {
            DatabaseContract.Favor.TABLE_NAME + "." + DatabaseContract.Favor._ID,
            DatabaseContract.Favor.COLUMN_TITLE,
            DatabaseContract.Favor.COLUMN_DESCRIPTION,
            DatabaseContract.Favor.COLUMN_DISTANCE
    };
    private FavorsAdapter mAdapter;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        RxPermissions rxPermissions = new RxPermissions(this.getActivity());
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (lastLocation != null)
                            getFavors(lastLocation);
                    }
                });
    }

    private void getFavors(Location location) {
        favorsFeedVM.getNearbyFavors(location.getLongitude(),
                location.getLatitude())
                .subscribe(favors -> populateFavors(),
                        throwable -> Log.i("ERROR", throwable.getLocalizedMessage()));
    }

    private void populateFavors() {
        getLoaderManager().restartLoader(0, null, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

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

        String sortOrder = DatabaseContract.Favor.COLUMN_DISTANCE + " ASC";
        Uri FavorsUri = DatabaseContract.Favor.CONTENT_URI;

        return new CursorLoader(getActivity(),
                FavorsUri,
                FAVORS_COLUMNS,
                null,
                null,
                sortOrder);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
