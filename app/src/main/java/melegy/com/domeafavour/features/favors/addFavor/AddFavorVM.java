package melegy.com.domeafavour.features.favors.addFavor;


import melegy.com.domeafavour.shared.models.resources.Favor;
import melegy.com.domeafavour.shared.models.responses.AddFavorResponse;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public interface AddFavorVM {

    Observable<AddFavorResponse> addFavor(Favor favor);
}
