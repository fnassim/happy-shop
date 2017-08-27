package sephora.happyshop.di.components;


import dagger.Component;
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.ui.Adapters.ProductRecyclerViewAdapter;
import sephora.happyshop.ui.Fragments.CategoryFragment;
import sephora.happyshop.mvvm.ViewModels.MainActivityViewModel;
import sephora.happyshop.mvvm.ViewModels.ProductActivityViewModel;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.modules.MainActivityModule;
import sephora.happyshop.di.scopes.MainActivityScope;

/**
 * Created by fadel on 25/8/17.
 */

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    ActivityMainBinding activityMainBinding();
    MainActivityViewModel mainActivityViewModel();

    void inject(MainActivity activity);
    void inject(CategoryFragment fragment);
    void inject(ProductRecyclerViewAdapter adapter);
    void inject(ProductActivityViewModel viewModel);
}
