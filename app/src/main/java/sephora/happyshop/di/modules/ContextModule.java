package sephora.happyshop.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sephora.happyshop.di.scopes.HappyShopApplicationScope;

/**
 * Created by fadel on 24/8/17.
 */

@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @HappyShopApplicationScope
    @Provides
    Context provideContext() {
        return mContext;
    }
}
