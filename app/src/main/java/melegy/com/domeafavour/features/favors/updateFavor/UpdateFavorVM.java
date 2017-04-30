package melegy.com.domeafavour.features.favors.updateFavor;

import javax.inject.Inject;

import melegy.com.domeafavour.data.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class UpdateFavorVM {

    @Inject
    UpdateFavorApiService updateFavorApiService;

    public Observable<Favor> addBenefactorToFavor(String userID) {
        return updateFavorApiService.addBenefactorToFavor(userID);
    }

    public Observable<Favor> markFavorAsDone(String favorId) {
        return updateFavorApiService.markFavorAsDone(favorId);
    }
}
