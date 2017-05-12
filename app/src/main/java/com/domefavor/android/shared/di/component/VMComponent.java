
package com.domefavor.android.shared.di.component;

import com.domefavor.android.features.authentication.register.SignInActivity;
import com.domefavor.android.features.favors.addFavor.AddFavorFragment;
import com.domefavor.android.features.favors.favorsFeed.FeedActivityFragment;
import com.domefavor.android.shared.di.modules.VMModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules =  VMModule.class)
public interface VMComponent {

    void inject(FeedActivityFragment feedActivityFragment);

    void inject(AddFavorFragment addFavorFragment);

    void inject(SignInActivity signInActivity);
}
