package melegy.com.domeafavour.features.favors.addFavor;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.NetworkApi;
import melegy.com.domeafavour.shared.models.resources.Favor;
import melegy.com.domeafavour.shared.models.responses.AddFavorResponse;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class AddFavorApiService {
    @Inject
    NetworkApi networkApi;

    public AddFavorApiService(){
        App.getApp().getNetComponent().inject(this);
    }

    Observable<AddFavorResponse> addFavor(Favor favor){
        return networkApi.addFavor(favor);
    }
}
