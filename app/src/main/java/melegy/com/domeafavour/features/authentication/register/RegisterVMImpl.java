package melegy.com.domeafavour.features.authentication.register;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.shared.models.resources.User;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class RegisterVMImpl implements RegisterVM {

    @Inject
    RegisterApiService registerApiService;

    public RegisterVMImpl(){
        App.getApp().getApiComponent().inject(this);
    }
    @Override
    public Observable<User> register(User user) {
        return registerApiService.register(user);
    }
}
