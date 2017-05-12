package com.domefavor.android.shared.di.modules;

import com.domefavor.android.features.authentication.register.RegisterVM;
import com.domefavor.android.features.favors.addFavor.AddFavorVM;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedVM;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ahmad on 4/17/17.
 */

@Module
public class VMModule {

    @Provides
    @Singleton
    FavorsFeedVM provideFavorsFeedVM(){
        return new FavorsFeedVM();
    }

    @Provides
    @Singleton
    AddFavorVM provideAddFavorVM(){
        return new AddFavorVM();
    }

    @Provides
    @Singleton
    RegisterVM provideRegisterVM(){
        return new RegisterVM();
    }

}
