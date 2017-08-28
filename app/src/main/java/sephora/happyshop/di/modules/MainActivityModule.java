package sephora.happyshop.di.modules;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.R;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.scopes.MainActivityScope;
import sephora.happyshop.mvvm.Models.Products;
import sephora.happyshop.mvvm.ViewModels.MainActivityViewModel;
import sephora.happyshop.ui.Activities.MainActivity;

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
    ActivityMainBinding mainActivityBinding() {
        return DataBindingUtil.setContentView(mActivity, R.layout.activity_main);
    }

    @Provides
    @MainActivityScope
    MainActivityViewModel mainActivityViewModel() {
        return ViewModelProviders.of(mActivity).get(MainActivityViewModel.class);
    }

    @Provides
    @MainActivityScope
    ObservableBoolean observableBoolean() {
        return new ObservableBoolean(false);
    }

    @Provides
    @MainActivityScope
    BehaviorSubject<Products> behaviorSubjects() {
        return BehaviorSubject.create();
    }
}
