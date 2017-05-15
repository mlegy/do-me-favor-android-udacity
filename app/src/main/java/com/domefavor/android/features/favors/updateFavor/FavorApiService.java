package com.domefavor.android.features.favors.updateFavor;


import com.domefavor.android.App;
import com.domefavor.android.data.models.resources.Favor;
import com.domefavor.android.data.models.responses.FavorActionResponse;
import com.domefavor.android.shared.NetworkApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ahmad on 4/22/17.
 */

public class FavorApiService {

    @Inject
    NetworkApi networkApi;

    public FavorApiService(){
        App.getApp().getNetComponent().inject(this);
    }

    Observable<Favor> getFavorById(String favorId){
        return networkApi.getFavorById(favorId);
    }

    Observable<FavorActionResponse> addBenefactorToFavor(String userId){
        return networkApi.addBenefactorToFavor(userId);
    }

    Observable<FavorActionResponse> markFavorAsDone(String favorId){
        return networkApi.markFavorAsDone(favorId);
    }

}
