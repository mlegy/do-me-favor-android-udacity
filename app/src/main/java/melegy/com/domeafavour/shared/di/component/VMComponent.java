
package melegy.com.domeafavour.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import melegy.com.domeafavour.features.favors.favorsFeed.FeedActivityFragment;
import melegy.com.domeafavour.shared.di.modules.VMModule;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules =  VMModule.class)
public interface VMComponent {

    void inject(FeedActivityFragment feedActivityFragment);

}
