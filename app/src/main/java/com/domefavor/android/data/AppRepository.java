package com.domefavor.android.data;

import java.util.List;

import javax.inject.Inject;

import com.domefavor.android.App;
import com.domefavor.android.data.local.AppLocalDataStore;
import com.domefavor.android.data.models.resources.Favor;
import com.domefavor.android.data.remote.AppRemoteDataStore;
import com.domefavor.android.shared.Utility;
import rx.Observable;

/**
 * Created by ahmad on 4/28/17.
 */

public class AppRepository implements AppDataStore {

    @Inject
    AppLocalDataStore localDataStore;

    @Inject
    AppRemoteDataStore remoteDataStore;

    public AppRepository() {
        App.getApp().getDataStoreComponent().inject(this);
    }

    @Override
    public Observable<List<Favor>> getFavors(double x, double y) {
        if (Utility.isConnectingToInternet()) return remoteDataStore.getFavors(x, y);
        else return localDataStore.getFavors(x, y);
    }
}
