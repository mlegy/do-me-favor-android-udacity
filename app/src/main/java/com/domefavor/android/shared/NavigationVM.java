
package com.domefavor.android.shared;

import android.content.SharedPreferences;

import com.domefavor.android.App;

import javax.inject.Inject;

import static com.domefavor.android.features.authentication.register.RegisterVM.USER_AVATAR_KEY;
import static com.domefavor.android.features.authentication.register.RegisterVM.USER_NAME_KEY;

/**
 * Created by ahmad on 5/19/17.
 */

public class NavigationVM {

    @Inject
    SharedPreferences sharedPreferences;

    public NavigationVM() {
        App.getApp().getApiComponent().inject(this);
    }

    String getUserFirstName() {
        return sharedPreferences.getString(USER_NAME_KEY, null);
    }

    String getUserAvatarURL() {
        return sharedPreferences.getString(USER_AVATAR_KEY, null);
    }
}
