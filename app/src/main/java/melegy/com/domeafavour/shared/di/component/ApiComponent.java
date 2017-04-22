
package melegy.com.domeafavour.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import melegy.com.domeafavour.features.authentication.register.RegisterVMImpl;
import melegy.com.domeafavour.features.favors.addFavor.AddFavorVMImpl;
import melegy.com.domeafavour.features.favors.favorsFeed.FavorsFeedVMImpl;
import melegy.com.domeafavour.features.favors.updateFavor.UpdateFavorVMImpl;
import melegy.com.domeafavour.shared.di.modules.ApiModule;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules =  ApiModule.class)
public interface ApiComponent {

    void inject(RegisterVMImpl registerVM);

    void inject(AddFavorVMImpl addFavorVM);

    void inject(FavorsFeedVMImpl favorsFeedVM);

    void inject(UpdateFavorVMImpl updateFavorVM);
}
