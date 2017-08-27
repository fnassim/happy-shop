package sephora.happyshop.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sephora.happyshop.api.ApiService;
import sephora.happyshop.constants.ApiConstants;
import sephora.happyshop.di.scopes.HappyShopApplicationScope;

/**
 * Created by fadel on 24/8/17.
 */

@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return ApiConstants.BASE_URL;
    }

    @Provides
    @HappyShopApplicationScope
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @HappyShopApplicationScope
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @HappyShopApplicationScope
    @Provides
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxFactory, GsonConverterFactory gsonFactory, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxFactory)
                .addConverterFactory(gsonFactory)
                .build();
    }

    @Provides
    @HappyShopApplicationScope
    ApiService provideProductsApi(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}