package com.domefavor.android.shared.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.domefavor.android.features.favors.addFavor.AddFavorVM;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedVM;

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

}
