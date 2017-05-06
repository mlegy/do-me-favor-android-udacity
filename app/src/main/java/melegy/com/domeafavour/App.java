
package melegy.com.domeafavour;

/**
 * Created by ahmad on 4/17/17.
 */

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

import melegy.com.domeafavour.shared.di.component.ApiComponent;
import melegy.com.domeafavour.shared.di.component.DaggerApiComponent;
import melegy.com.domeafavour.shared.di.component.DaggerDataStoreComponent;
import melegy.com.domeafavour.shared.di.component.DaggerNetComponent;
import melegy.com.domeafavour.shared.di.component.DaggerVMComponent;
import melegy.com.domeafavour.shared.di.component.DataStoreComponent;
import melegy.com.domeafavour.shared.di.component.NetComponent;
import melegy.com.domeafavour.shared.di.component.VMComponent;
import melegy.com.domeafavour.shared.di.modules.ApiModule;
import melegy.com.domeafavour.shared.di.modules.AppModule;
import melegy.com.domeafavour.shared.di.modules.DataStoreModule;
import melegy.com.domeafavour.shared.di.modules.NetModule;
import melegy.com.domeafavour.shared.di.modules.VMModule;

/**
 * Created by ahmad on 2/26/17.
 */

public class App extends Application {
    private static App app;
    private NetComponent mNetComponent;
    private ApiComponent mApiComponent;
    private DataStoreComponent mDataStoreComponent;
    private VMComponent mVMComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this);
        Fresco.initialize(this);
        app = this;
        initNetComponent();
        initApiComponent();
        initDataStoreComponent();
        initVMComponent();
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

    private void initVMComponent() {
        mVMComponent = DaggerVMComponent.builder()
                .vMModule(new VMModule())
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

    public VMComponent getVMComponent() {
        return mVMComponent;
    }

}
