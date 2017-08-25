package sephora.happyshop.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import sephora.happyshop.Api.ApiService;
import sephora.happyshop.MVVM.ViewModels.MainActivityViewModel;
import sephora.happyshop.di.modules.ContextModule;
import sephora.happyshop.di.modules.NetworkModule;

/**
 * Created by fadel on 24/8/17.
 */
@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Retrofit getRetrofit();
    Context getContext();
    ApiService getApiService();

    void inject(MainActivityViewModel viewModel);
}
