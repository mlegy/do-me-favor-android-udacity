package com.domefavor.android.features.favors.addFavor;

import javax.inject.Inject;

import com.domefavor.android.App;
import com.domefavor.android.data.models.requests.AddFavorRequest;
import com.domefavor.android.data.models.responses.AddFavorResponse;
import com.domefavor.android.shared.NetworkApi;
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
