package sephora.happyshop.di.modules;

import android.databinding.DataBindingUtil;

import dagger.Module;
import dagger.Provides;
import sephora.happyshop.Activities.MainActivity;
import sephora.happyshop.R;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.scopes.MainActivityScope;

/**
 * Created by fadel on 25/8/17.
 */

@Module
public class MainActivityModule {
    private MainActivity mActivity;

    public MainActivityModule(MainActivity activity) {
        mActivity = activity;
    }

    @Provides
    @MainActivityScope
    public ActivityMainBinding mainActivityBinding() {
        return DataBindingUtil.setContentView(mActivity, R.layout.activity_main);
    }
}
