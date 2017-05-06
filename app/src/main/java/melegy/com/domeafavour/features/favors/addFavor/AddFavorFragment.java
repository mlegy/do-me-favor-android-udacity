
package melegy.com.domeafavour.features.favors.addFavor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import melegy.com.domeafavour.App;
import melegy.com.domeafavour.R;
import melegy.com.domeafavour.features.favors.favorsFeed.FeedActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFavorFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    @BindView(R.id.editText_title)
    TextInputEditText editTextTitle;

    @BindView(R.id.editText_description)
    TextInputEditText editTextDescription;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Inject
    AddFavorVM addFavorVM;

    private GoogleApiClient mGoogleApiClient;

    public AddFavorFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_favor, container, false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(this::submitFavor);

        return view;
    }

    @SuppressLint("MissingPermission")
    private void submitFavor(View view) {
        RxPermissions rxPermissions = new RxPermissions(this.getActivity());
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (lastLocation != null) {
                            String title = editTextTitle.getText().toString();
                            String description = editTextDescription.getText().toString();
                            addFavorVM.addFavor(title, description, lastLocation)
                                    .subscribe(x -> FeedActivity.start(this.getActivity()),
                                            throwable -> showMessage(view,
                                                    R.string.something_wrong_error_message));
                        }
                    } else
                        showMessage(view, R.string.no_permission_error_message);
                });
    }

    private void showMessage(View view, int messageStringId) {
        Snackbar.make(view, getString(messageStringId), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

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

}
