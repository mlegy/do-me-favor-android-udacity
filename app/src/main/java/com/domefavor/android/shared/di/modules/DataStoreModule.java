package com.domefavor.android.shared.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.domefavor.android.data.local.AppLocalDataStore;
import com.domefavor.android.data.remote.AppRemoteDataStore;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedApiService;

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
