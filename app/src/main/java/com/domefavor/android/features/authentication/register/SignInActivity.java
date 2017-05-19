
package com.domefavor.android.features.authentication.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.domefavor.android.App;
import com.domefavor.android.R;
import com.domefavor.android.features.favors.favorsFeed.FeedActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;

    @Inject
    RegisterVM registerVM;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.image_logo)
    ImageView logo;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp().getVMComponent().inject(this);

        if (registerVM.isRegisteredUser())
            FeedActivity.start(this);

        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);

        initGoogleApiClient();

        signInButton.setSize(SignInButton.SIZE_WIDE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void initGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            progressBar.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
            logo.setVisibility(View.GONE);
            GoogleSignInAccount acct = result.getSignInAccount();
            registerVM.register(acct).subscribe(user -> FeedActivity.start(this),
                    throwable -> Log.e("ERROR", throwable.getMessage()));
        }
    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
