package sephora.happyshop.di.components;

import android.content.Context;

import dagger.Component;
import sephora.happyshop.Api.ApiService;
import sephora.happyshop.MVVM.ViewModels.MainActivityViewModel;
import sephora.happyshop.di.modules.ContextModule;
import sephora.happyshop.di.modules.NetworkModule;
import sephora.happyshop.di.scopes.HappyShopApplicationScope;

/**
 * Created by fadel on 24/8/17.
 */
@HappyShopApplicationScope
@Component(modules = {ContextModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Context getContext();
    ApiService getApiService();

    void inject(MainActivityViewModel viewModel);
}
