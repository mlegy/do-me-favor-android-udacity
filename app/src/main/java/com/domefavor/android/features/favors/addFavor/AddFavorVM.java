
package com.domefavor.android.features.favors.addFavor;

import android.content.SharedPreferences;
import android.location.Location;

import com.domefavor.android.App;
import com.domefavor.android.data.models.requests.AddFavorRequest;
import com.domefavor.android.data.models.responses.AddFavorResponse;
import com.domefavor.android.features.authentication.register.RegisterVM;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ahmad on 4/17/17.
 */

public class AddFavorVM {
    @Inject
    AddFavorApiService addFavorApiService;

    @Inject
    SharedPreferences sharedPreferences;

    public AddFavorVM() {
        App.getApp().getApiComponent().inject(this);
    }

    public Observable<AddFavorResponse> addFavor(String title, String description,
            Location location) {

        String userId = sharedPreferences.getString(RegisterVM.USER_ID_KEY, null);
        if (userId != null) {
            AddFavorRequest addFavorRequest = AddFavorRequest.create(title, description,
                    userId,
                    com.domefavor.android.data.models.resources.Location
                            .create(location.getLongitude(), location.getLatitude()));
            return addFavorApiService.addFavor(addFavorRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return null;
        }
    }
}
