package sephora.happyshop.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Component;
import sephora.happyshop.api.ApiService;
import sephora.happyshop.di.modules.ContextModule;
import sephora.happyshop.di.modules.NetworkModule;
import sephora.happyshop.di.modules.SharedPrefsModule;
import sephora.happyshop.di.scopes.HappyShopApplicationScope;
import sephora.happyshop.mvvm.ViewModels.MainActivityViewModel;

/**
 * Created by fadel on 24/8/17.
 */
@HappyShopApplicationScope
@Component(modules = {SharedPrefsModule.class, ContextModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Context getContext();

    ApiService getApiService();

    SharedPreferences sharedPrefs();

    SharedPreferences.Editor editor();

    void inject(MainActivityViewModel viewModel);
}
