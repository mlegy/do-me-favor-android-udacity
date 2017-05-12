
package com.domefavor.android.shared.di.component;

import javax.inject.Singleton;

import dagger.Component;
import com.domefavor.android.features.authentication.register.RegisterVM;
import com.domefavor.android.features.favors.addFavor.AddFavorVM;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedVM;
import com.domefavor.android.features.favors.updateFavor.UpdateFavorVM;
import com.domefavor.android.shared.di.modules.ApiModule;

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
