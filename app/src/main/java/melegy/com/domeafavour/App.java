
package melegy.com.domeafavour;

/**
 * Created by ahmad on 4/17/17.
 */

import android.app.Application;

import melegy.com.domeafavour.shared.di.component.ApiComponent;
import melegy.com.domeafavour.shared.di.component.DaggerApiComponent;
import melegy.com.domeafavour.shared.di.component.DaggerDataStoreComponent;
import melegy.com.domeafavour.shared.di.component.DaggerNetComponent;
import melegy.com.domeafavour.shared.di.component.DataStoreComponent;
import melegy.com.domeafavour.shared.di.component.NetComponent;
import melegy.com.domeafavour.shared.di.modules.ApiModule;
import melegy.com.domeafavour.shared.di.modules.AppModule;
import melegy.com.domeafavour.shared.di.modules.DataStoreModule;
import melegy.com.domeafavour.shared.di.modules.NetModule;

/**
 * Created by ahmad on 2/26/17.
 */

public class App extends Application {
    private static App app;
    private NetComponent mNetComponent;
    private ApiComponent mApiComponent;
    private DataStoreComponent mDataStoreComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initNetComponent();
        initApiComponent();
        initDataStoreComponent();
    }

    private void initNetComponent() {
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    private void initApiComponent() {
        mApiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    private void initDataStoreComponent() {
        mDataStoreComponent = DaggerDataStoreComponent.builder()
                .appModule(new AppModule(this))
                .dataStoreModule(new DataStoreModule())
                .build();
    }

    public static App getApp() {
        return app;
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public DataStoreComponent getDataStoreComponent() {
        return mDataStoreComponent;
    }

}
