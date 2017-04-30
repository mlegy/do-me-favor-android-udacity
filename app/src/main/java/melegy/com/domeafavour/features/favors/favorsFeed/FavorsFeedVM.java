package melegy.com.domeafavour.features.favors.favorsFeed;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.data.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class FavorsFeedVM {

    @Inject
    FavorsFeedApiService favorsFeedApiService;

    public Observable<List<Favor>> getNearbyFavors(long x, long y) {
        return favorsFeedApiService.getNearbyFavor(x, y);
    }
}
