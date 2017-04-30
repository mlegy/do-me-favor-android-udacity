package melegy.com.domeafavour.features.favors.updateFavor;


import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.shared.NetworkApi;
import melegy.com.domeafavour.data.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/22/17.
 */

public class UpdateFavorApiService {

    @Inject
    NetworkApi networkApi;

    public UpdateFavorApiService(){
        App.getApp().getNetComponent().inject(this);
    }

    Observable<Favor> addBenefactorToFavor(String userId){
        return networkApi.addBenefactorToFavor(userId);
    }

    Observable<Favor> markFavorAsDone(String favorId){
        return networkApi.markFavorAsDone(favorId);
    }

}
