package melegy.com.domeafavour.features.favors.favorsFeed;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.shared.NetworkApi;
import melegy.com.domeafavour.data.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class FavorsFeedApiService {

    @Inject
    NetworkApi networkApi;

    public FavorsFeedApiService(){
        App.getApp().getNetComponent().inject(this);
    }

    public Observable<List<Favor>> getNearbyFavor(double x, double y){
        return networkApi.getNearbyFavors(x, y);
    }
}
