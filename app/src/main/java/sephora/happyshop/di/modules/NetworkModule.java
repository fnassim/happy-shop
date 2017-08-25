package sephora.happyshop.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sephora.happyshop.Api.ApiService;
import sephora.happyshop.Constants.ApiConstants;

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

    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxFactory, GsonConverterFactory gsonFactory, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxFactory)
                .addConverterFactory(gsonFactory)
                .build();
    }

    @Singleton
    @Provides
    ApiService provideProductsApi(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}