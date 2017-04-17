package melegy.com.domeafavour.shared.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import melegy.com.domeafavour.features.authentication.register.RegisterApiService;

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
}
