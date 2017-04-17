
package melegy.com.domeafavour.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import melegy.com.domeafavour.features.authentication.register.RegisterApiService;
import melegy.com.domeafavour.shared.di.modules.AppModule;
import melegy.com.domeafavour.shared.di.modules.NetModule;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules = {
        AppModule.class, NetModule.class
})
public interface NetComponent {

    void inject(RegisterApiService registerApiService);
}
