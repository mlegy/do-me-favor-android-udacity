
package melegy.com.domeafavour.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import melegy.com.domeafavour.features.authentication.register.RegisterVM;
import melegy.com.domeafavour.features.favors.addFavor.AddFavorVM;
import melegy.com.domeafavour.features.favors.favorsFeed.FavorsFeedVM;
import melegy.com.domeafavour.features.favors.updateFavor.UpdateFavorVM;
import melegy.com.domeafavour.shared.di.modules.ApiModule;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules =  ApiModule.class)
public interface ApiComponent {

    void inject(RegisterVM registerVM);

    void inject(AddFavorVM addFavorVM);

    void inject(FavorsFeedVM favorsFeedVM);

    void inject(UpdateFavorVM updateFavorVM);
}
