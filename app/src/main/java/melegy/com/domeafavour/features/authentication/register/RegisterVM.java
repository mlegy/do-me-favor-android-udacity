package melegy.com.domeafavour.features.authentication.register;


import melegy.com.domeafavour.shared.models.resources.User;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

interface RegisterVM {

    Observable<User> register(User user);
}
