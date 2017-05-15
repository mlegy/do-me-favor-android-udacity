
package com.domefavor.android.features.favors.addFavor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.domefavor.android.App;
import com.domefavor.android.R;
import com.domefavor.android.features.favors.favorsFeed.FeedActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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

                            SharedPreferences prefs = PreferenceManager
                                    .getDefaultSharedPreferences(getContext());
                            String user_id = prefs.getString("user_id", null);

                            if (user_id != null) {
                                // making async task, just to meet udacity requirements.
                                new SendPostRequest(getActivity()).execute(title, description,
                                        user_id,
                                        lastLocation.getLongitude() + "",
                                        lastLocation.getLatitude() + "");
                            }
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

    public static class SendPostRequest extends AsyncTask<String, Void, Integer> {
        Context context;

        private SendPostRequest(Context context) {
            this.context = context.getApplicationContext();
        }

        protected Integer doInBackground(String... params) {
            try {
                URL url = new URL(
                        "https://apricot-cobbler-23847.herokuapp.com/favors?access_token=xE33zWa5TKCRNbGb6X8bvrv9erB5xh95k3a9fcAP");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("title", params[0]);
                postDataParams.put("description", params[1]);
                postDataParams.put("owner", params[2]);

                JSONObject location = new JSONObject();
                location.put("long", Double.valueOf(params[3]));
                location.put("lat", Double.valueOf(params[4]));

                postDataParams.put("location", location);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(postDataParams.toString());

                writer.flush();
                writer.close();
                os.close();

                return conn.getResponseCode();

            } catch (Exception e) {
                return 500;
            }

        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 200) {
                FeedActivity.start(context);
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
