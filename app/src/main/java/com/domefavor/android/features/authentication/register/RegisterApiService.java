package com.domefavor.android.features.authentication.register;


import javax.inject.Inject;

import com.domefavor.android.App;
import com.domefavor.android.shared.NetworkApi;
import com.domefavor.android.data.models.resources.User;
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

    Observable<User> register(User user){
        return networkApi.addUser(user);
    }
}
