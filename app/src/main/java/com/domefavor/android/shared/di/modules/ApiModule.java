package com.domefavor.android.shared.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.domefavor.android.data.AppRepository;
import com.domefavor.android.features.authentication.register.RegisterApiService;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedApiService;
import com.domefavor.android.features.favors.updateFavor.FavorApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ahmad on 4/17/17.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    RegisterApiService provideRegisterApiService(){
        return new RegisterApiService();
    }

    @Provides
    @Singleton
    FavorsFeedApiService provideFavorsFeedApiService(){
        return new FavorsFeedApiService();
    }

    @Provides
    @Singleton
    FavorApiService provideUpdateFavorApiService(){
        return new FavorApiService();
    }

    @Provides
    @Singleton
    AppRepository provideAppRepository(){
        return new AppRepository();
    }
}
