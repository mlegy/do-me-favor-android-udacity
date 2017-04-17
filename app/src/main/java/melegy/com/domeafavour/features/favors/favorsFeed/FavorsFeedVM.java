package melegy.com.domeafavour.features.favors.favorsFeed;

import java.util.List;

import melegy.com.domeafavour.shared.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public interface FavorsFeedVM {

    Observable<List<Favor>> getNearbyFavors(long x, long y);
}
