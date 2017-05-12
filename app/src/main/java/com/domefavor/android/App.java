
package com.domefavor.android;

/**
 * Created by ahmad on 4/17/17.
 */

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

import com.domefavor.android.shared.di.component.ApiComponent;
import com.domefavor.android.shared.di.component.DaggerApiComponent;
import com.domefavor.android.shared.di.component.DaggerDataStoreComponent;
import com.domefavor.android.shared.di.component.DaggerNetComponent;
import com.domefavor.android.shared.di.component.DaggerVMComponent;
import com.domefavor.android.shared.di.component.DataStoreComponent;
import com.domefavor.android.shared.di.component.NetComponent;
import com.domefavor.android.shared.di.component.VMComponent;
import com.domefavor.android.shared.di.modules.ApiModule;
import com.domefavor.android.shared.di.modules.AppModule;
import com.domefavor.android.shared.di.modules.DataStoreModule;
import com.domefavor.android.shared.di.modules.NetModule;
import com.domefavor.android.shared.di.modules.VMModule;

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
