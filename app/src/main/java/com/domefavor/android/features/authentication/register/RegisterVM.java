
package com.domefavor.android.features.authentication.register;

import android.content.SharedPreferences;

import com.domefavor.android.App;
import com.domefavor.android.data.models.requests.SignUpRequest;
import com.domefavor.android.data.models.resources.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmad on 4/17/17.
 */

public class RegisterVM {

    @Inject
    RegisterApiService registerApiService;

    @Inject
    SharedPreferences sharedPreferences;

    public static final String USER_ID_KEY = "user_id";
    public static final String USER_NAME_KEY = "user_name";
    public static final String USER_AVATAR_KEY = "user_avatar";

    public RegisterVM() {
        App.getApp().getApiComponent().inject(this);
    }

    public Observable<User> register(GoogleSignInAccount account) {

        SignUpRequest user = SignUpRequest.create(account.getGivenName(), account.getFamilyName(),
                account.getEmail(),
                account.getPhotoUrl().toString());
        return registerApiService.register(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnNext(this::handleSuccessLogin);
    }

    private void handleSuccessLogin(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID_KEY, user.id());
        editor.putString(USER_NAME_KEY, user.firstName());
        editor.putString(USER_AVATAR_KEY, user.avatar());
        editor.apply();
    }

    boolean isRegisteredUser() {
        return sharedPreferences.getString(USER_ID_KEY, null) != null;
    }
}
