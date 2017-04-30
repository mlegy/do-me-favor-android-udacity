package melegy.com.domeafavour.data;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.App;
import melegy.com.domeafavour.data.local.AppLocalDataStore;
import melegy.com.domeafavour.data.models.resources.Favor;
import melegy.com.domeafavour.data.remote.AppRemoteDataStore;
import melegy.com.domeafavour.shared.Utility;
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
    public Observable<List<Favor>> getFavors() {
        if (Utility.isConnectingToInternet())
            return remoteDataStore.getFavors();
        else return localDataStore.getFavors();
    }


}
