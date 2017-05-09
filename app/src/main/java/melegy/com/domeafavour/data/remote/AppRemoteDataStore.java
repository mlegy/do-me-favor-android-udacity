
package melegy.com.domeafavour.data.remote;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.data.AppDataStore;
import melegy.com.domeafavour.data.local.AppLocalDataStore;
import melegy.com.domeafavour.data.models.resources.Favor;
import melegy.com.domeafavour.features.favors.favorsFeed.FavorsFeedApiService;
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
