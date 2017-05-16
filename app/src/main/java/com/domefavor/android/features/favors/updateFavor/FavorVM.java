
package com.domefavor.android.features.favors.updateFavor;

import android.content.SharedPreferences;

import com.domefavor.android.App;
import com.domefavor.android.data.models.resources.Favor;
import com.domefavor.android.data.models.responses.FavorActionResponse;
import com.domefavor.android.features.authentication.register.RegisterVM;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmad on 4/17/17.
 */

public class FavorVM {

    @Inject
    FavorApiService favorApiService;

    @Inject
    SharedPreferences sharedPreferences;

    public FavorVM() {
        App.getApp().getApiComponent().inject(this);
    }

    Observable<Favor> getFavorById(String favorId) {
        return favorApiService.getFavorById(favorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<FavorActionResponse> addBenefactorToFavor() {
        return favorApiService
                .addBenefactorToFavor(sharedPreferences.getString(RegisterVM.USER_ID_KEY, null))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<FavorActionResponse> markFavorAsDone(String favorId) {
        return favorApiService.markFavorAsDone(favorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    boolean isFavorOwner(String favorOwnerId) {
        return sharedPreferences.getString(RegisterVM.USER_ID_KEY, null).equals(favorOwnerId);

    }
}
