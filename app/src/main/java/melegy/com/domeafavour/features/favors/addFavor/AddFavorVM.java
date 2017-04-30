package melegy.com.domeafavour.features.favors.addFavor;

import javax.inject.Inject;

import melegy.com.domeafavour.data.models.resources.Favor;
import melegy.com.domeafavour.data.models.responses.AddFavorResponse;
import rx.Observable;

/**
 * Created by ahmad on 4/17/17.
 */

public class AddFavorVM {
    @Inject
    AddFavorApiService addFavorApiService;

    public Observable<AddFavorResponse> addFavor(Favor favor) {
        return addFavorApiService.addFavor(favor);
    }
}
