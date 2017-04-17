package melegy.com.domeafavour.features.authentication.register;


import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.NetworkApi;
import melegy.com.domeafavour.shared.models.resources.User;
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
