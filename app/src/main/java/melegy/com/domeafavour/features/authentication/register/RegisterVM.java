package melegy.com.domeafavour.features.authentication.register;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.data.models.resources.User;
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
