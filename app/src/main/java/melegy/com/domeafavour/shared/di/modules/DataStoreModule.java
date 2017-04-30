package melegy.com.domeafavour.shared.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import melegy.com.domeafavour.data.local.AppLocalDataStore;
import melegy.com.domeafavour.data.remote.AppRemoteDataStore;
import melegy.com.domeafavour.features.favors.favorsFeed.FavorsFeedApiService;

/**
 * Created by ahmad on 4/17/17.
 */

@Module
public class DataStoreModule {

    @Provides
    @Singleton
    AppLocalDataStore provideAppLocalDataStore(Application application){
        return new AppLocalDataStore(application);
    }

    @Provides
    @Singleton
    AppRemoteDataStore provideAppRemoteDataStore(){
        return new AppRemoteDataStore();
    }

    @Provides
    @Singleton
    FavorsFeedApiService provideFavorsFeedApiService(){
        return new FavorsFeedApiService();
    }

}
