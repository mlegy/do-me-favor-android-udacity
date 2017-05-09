
package melegy.com.domeafavour.features.favors.addFavor;

import android.location.Location;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.data.models.requests.AddFavorRequest;
import melegy.com.domeafavour.data.models.responses.AddFavorResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmad on 4/17/17.
 */

public class AddFavorVM {
    @Inject
    AddFavorApiService addFavorApiService;

    public AddFavorVM() {
        App.getApp().getApiComponent().inject(this);
    }

    public Observable<AddFavorResponse> addFavor(String title, String description,
            Location location) {
        AddFavorRequest addFavorRequest = AddFavorRequest.create(title, description,
                "590cf6e0f8970d00119c2c5b",
                melegy.com.domeafavour.data.models.resources.Location
                        .create(location.getLongitude(), location.getLatitude()));
        return addFavorApiService.addFavor(addFavorRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
