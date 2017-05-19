
package com.domefavor.android.shared.di.component;

import com.domefavor.android.features.authentication.register.RegisterApiService;
import com.domefavor.android.features.favors.favorsFeed.FavorsFeedApiService;
import com.domefavor.android.features.favors.updateFavor.FavorApiService;
import com.domefavor.android.shared.di.modules.AppModule;
import com.domefavor.android.shared.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ahmad on 4/10/17.
 */

@Singleton
@Component(modules = {
        AppModule.class, NetModule.class
})
public interface NetComponent {

    void inject(RegisterApiService registerApiService);

    void inject(FavorsFeedApiService favorsFeedApiService);

    void inject(FavorApiService favorApiService);
}
