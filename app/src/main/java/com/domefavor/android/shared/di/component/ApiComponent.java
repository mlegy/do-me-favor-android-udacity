
package com.domefavor.android.shared.di.component;

import com.domefavor.android.features.authentication.register.RegisterVM;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedVM;
import com.domefavor.android.features.favors.updateFavor.FavorVM;
import com.domefavor.android.shared.di.modules.ApiModule;
import com.domefavor.android.shared.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules = {
        AppModule.class, ApiModule.class
})
public interface ApiComponent {

    void inject(RegisterVM registerVM);

    void inject(FavorsFeedVM favorsFeedVM);

    void inject(FavorVM favorVM);
}
