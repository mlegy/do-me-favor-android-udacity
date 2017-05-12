package com.domefavor.android.features.favors.updateFavor;


import javax.inject.Inject;

import com.domefavor.android.App;
import com.domefavor.android.shared.NetworkApi;
import com.domefavor.android.data.models.resources.Favor;
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
