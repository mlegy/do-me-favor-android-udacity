
package melegy.com.domeafavour.features.favors.favorsFeed;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.data.AppRepository;
import melegy.com.domeafavour.data.models.resources.Favor;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmad on 4/17/17.
 */

public class FavorsFeedVM {

    @Inject
    FavorsFeedApiService favorsFeedApiService;

    @Inject
    AppRepository appRepository;

    public FavorsFeedVM() {
        App.getApp().getApiComponent().inject(this);
    }

    Observable<List<Favor>> getNearbyFavors(double x, double y) {
        return appRepository.getFavors(x, y)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
