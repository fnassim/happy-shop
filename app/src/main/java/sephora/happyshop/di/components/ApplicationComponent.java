package sephora.happyshop.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Component;
import sephora.happyshop.Api.ApiService;
import sephora.happyshop.MVVM.ViewModels.MainActivityViewModel;
import sephora.happyshop.MVVM.ViewModels.ProductActivityViewModel;
import sephora.happyshop.di.modules.ContextModule;
import sephora.happyshop.di.modules.NetworkModule;
import sephora.happyshop.di.modules.SharedPrefsModule;
import sephora.happyshop.di.scopes.HappyShopApplicationScope;

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
