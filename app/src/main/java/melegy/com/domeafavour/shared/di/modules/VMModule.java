package melegy.com.domeafavour.shared.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import melegy.com.domeafavour.features.favors.favorsFeed.FavorsFeedVM;

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

}
