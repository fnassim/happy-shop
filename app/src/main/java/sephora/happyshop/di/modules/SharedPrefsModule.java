package sephora.happyshop.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import sephora.happyshop.constants.SharedPrefsConstants;
import sephora.happyshop.di.scopes.HappyShopApplicationScope;

/**
 * Created by fadel on 26/8/17.
 */

@Module
public class SharedPrefsModule {
    private static final String PREFS_NAME = "PREFS_NAME";

    @Provides
    @Named(PREFS_NAME)
    String provideBaseUrlString() {
        return SharedPrefsConstants.PREFS_NAME;
    }


    @Provides
    @HappyShopApplicationScope
    SharedPreferences sharedPreferences(Context context, @Named(PREFS_NAME) String prefsName) {
        return context.getSharedPreferences(prefsName, 0);
    }

    @Provides
    @HappyShopApplicationScope
    SharedPreferences.Editor sharedPrefsEditor(SharedPreferences prefs) {
        return prefs.edit();
    }
}
