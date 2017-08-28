package sephora.happyshop.di.components;


import android.databinding.ObservableBoolean;

import dagger.Component;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.modules.MainActivityModule;
import sephora.happyshop.di.scopes.MainActivityScope;
import sephora.happyshop.mvvm.Models.Products;
import sephora.happyshop.mvvm.ViewModels.MainActivityViewModel;
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.ui.Adapters.ProductRecyclerViewAdapter;
import sephora.happyshop.ui.Fragments.CategoryFragment;

/**
 * Created by fadel on 25/8/17.
 */

@MainActivityScope
@Component(modules = {MainActivityModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    ActivityMainBinding activityMainBinding();

    MainActivityViewModel mainActivityViewModel();

    ObservableBoolean observableBoolean();

    BehaviorSubject<Products> behaviourSubject();

    void inject(MainActivity activity);

    void inject(CategoryFragment fragment);

    void inject(ProductRecyclerViewAdapter adapter);

    void inject(MainActivityViewModel viewModel);
}
