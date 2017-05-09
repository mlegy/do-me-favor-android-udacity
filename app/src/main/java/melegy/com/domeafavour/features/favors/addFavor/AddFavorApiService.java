package melegy.com.domeafavour.features.favors.addFavor;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.data.models.requests.AddFavorRequest;
import melegy.com.domeafavour.data.models.responses.AddFavorResponse;
import melegy.com.domeafavour.shared.NetworkApi;
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

    Observable<AddFavorResponse> addFavor(AddFavorRequest favor){
        return networkApi.addFavor(favor);
    }
}
