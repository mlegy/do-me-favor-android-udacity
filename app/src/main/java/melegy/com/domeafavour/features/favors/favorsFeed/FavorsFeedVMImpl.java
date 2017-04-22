package melegy.com.domeafavour.features.favors.favorsFeed;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.shared.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class FavorsFeedVMImpl implements FavorsFeedVM {

    @Inject
    FavorsFeedApiService favorsFeedApiService;

    @Override
    public Observable<List<Favor>> getNearbyFavors(long x, long y) {
        return favorsFeedApiService.getNearbyFavor(x, y);
    }
}
