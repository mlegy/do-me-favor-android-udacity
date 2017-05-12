package com.domefavor.android.features.authentication.register;


import com.domefavor.android.App;
import com.domefavor.android.data.models.requests.SignUpRequest;
import com.domefavor.android.data.models.resources.User;
import com.domefavor.android.shared.NetworkApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class RegisterApiService {

    @Inject
    NetworkApi networkApi;

    public RegisterApiService(){
        App.getApp().getNetComponent().inject(this);
    }

    Observable<User> register(SignUpRequest user){
        return networkApi.addUser(user);
    }
}
