package com.domefavor.android.features.authentication.register;

import javax.inject.Inject;

import com.domefavor.android.App;
import com.domefavor.android.data.models.resources.User;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class RegisterVM {

    @Inject
    RegisterApiService registerApiService;

    public RegisterVM(){
        App.getApp().getApiComponent().inject(this);
    }

    public Observable<User> register(User user) {
        return registerApiService.register(user);
    }
}
