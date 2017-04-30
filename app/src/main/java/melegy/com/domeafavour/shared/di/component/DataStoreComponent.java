package melegy.com.domeafavour.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import melegy.com.domeafavour.data.AppRepository;
import melegy.com.domeafavour.data.local.AppLocalDataStore;
import melegy.com.domeafavour.data.remote.AppRemoteDataStore;
import melegy.com.domeafavour.shared.di.modules.AppModule;
import melegy.com.domeafavour.shared.di.modules.DataStoreModule;

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
