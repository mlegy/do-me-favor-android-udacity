package com.domefavor.android.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import com.domefavor.android.data.AppRepository;
import com.domefavor.android.data.local.AppLocalDataStore;
import com.domefavor.android.data.remote.AppRemoteDataStore;
import com.domefavor.android.shared.di.modules.AppModule;
import com.domefavor.android.shared.di.modules.DataStoreModule;

/**
 * Created by ahmad on 4/28/17.
 */

@Singleton
@Component(modules = {
        AppModule.class, DataStoreModule.class
})
public interface DataStoreComponent {

    void inject(AppRemoteDataStore remoteDataStore);

    void inject(AppLocalDataStore localDataStore);

    void inject(AppRepository repository);

}
