package melegy.com.domeafavour.shared.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import melegy.com.domeafavour.features.authentication.register.RegisterApiService;
import melegy.com.domeafavour.features.favors.addFavor.AddFavorApiService;
import melegy.com.domeafavour.features.favors.favorsFeed.FavorsFeedApiService;

/**
 * Created by ahmad on 4/17/17.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    RegisterApiService provideRegisterApiService(){
        return new RegisterApiService();
    }

    @Provides
    @Singleton
    AddFavorApiService provideAddFavorApiService(){
        return new AddFavorApiService();
    }

    @Provides
    @Singleton
    FavorsFeedApiService provideFavorsFeedApiService(){
        return new FavorsFeedApiService();
    }

    @Provides
    @Singleton
    FavorsFeedApiService favorsFeedApiService(){
        return new FavorsFeedApiService();
    }
}
