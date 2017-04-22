package melegy.com.domeafavour.features.favors.updateFavor;

import melegy.com.domeafavour.shared.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

interface UpdateFavorVM {

    Observable<Favor> addBenefactorToFavor(String userID);

    Observable<Favor> markFavorAsDone(String userID);

}
