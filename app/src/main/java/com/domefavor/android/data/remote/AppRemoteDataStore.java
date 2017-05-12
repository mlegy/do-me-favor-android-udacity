
package com.domefavor.android.data.remote;

import java.util.List;

import javax.inject.Inject;

import com.domefavor.android.App;
import com.domefavor.android.data.AppDataStore;
import com.domefavor.android.data.local.AppLocalDataStore;
import com.domefavor.android.data.models.resources.Favor;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedApiService;
import rx.Observable;


/**
 * Created by ahmad on 4/28/17.
 */

public class AppRemoteDataStore implements AppDataStore {

    @Inject
    AppLocalDataStore localDataStore;

    @Inject
    FavorsFeedApiService favorsFeedApiService;

    public AppRemoteDataStore() {
        App.getApp().getDataStoreComponent().inject(this);
    }

    @Override
    public Observable<List<Favor>> getFavors(double x, double y) {
        return favorsFeedApiService.getNearbyFavor(x, y)
                .doOnNext(favors -> localDataStore.performLocalStorage(favors));
    }
}
