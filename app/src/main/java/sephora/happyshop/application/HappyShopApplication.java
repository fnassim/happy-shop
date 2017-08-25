package sephora.happyshop.application;

import android.app.Application;

import sephora.happyshop.di.components.ApplicationComponent;
import sephora.happyshop.di.components.DaggerApplicationComponent;
import sephora.happyshop.di.modules.ContextModule;

/**
 * Created by fadel on 24/8/17.
 */

public class HappyShopApplication extends Application {
    private ApplicationComponent mApplicationComponent;
    private static HappyShopApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        mApplicationComponent = initDagger();
    }

    public static HappyShopApplication getApp() {
        return mApp;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    protected ApplicationComponent initDagger() {
        return DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
