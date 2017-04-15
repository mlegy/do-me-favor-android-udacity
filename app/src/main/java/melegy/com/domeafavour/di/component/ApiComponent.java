
package melegy.com.domeafavour.di.component;

import javax.inject.Singleton;

import dagger.Component;
import melegy.com.domeafavour.di.modules.ApiModule;
import melegy.com.domeafavour.di.modules.AppModule;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules = {
        AppModule.class, ApiModule.class
})
public interface ApiComponent {
}
