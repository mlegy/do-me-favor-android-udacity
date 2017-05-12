
package com.domefavor.android.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import com.domefavor.android.features.favors.addFavor.AddFavorFragment;
import com.domefavor.android.features.favors.favorsFeed.FeedActivityFragment;
import com.domefavor.android.shared.di.modules.VMModule;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules =  VMModule.class)
public interface VMComponent {

    void inject(FeedActivityFragment feedActivityFragment);

    void inject(AddFavorFragment addFavorFragment);
}
