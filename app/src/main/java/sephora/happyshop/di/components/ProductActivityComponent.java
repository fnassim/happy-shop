package sephora.happyshop.di.components;


import dagger.Component;
import sephora.happyshop.ui.Activities.ProductActivity;
import sephora.happyshop.mvvm.ViewModels.ProductActivityViewModel;
import sephora.happyshop.databinding.ActivityProductBinding;
import sephora.happyshop.di.modules.ProductActivityModule;
import sephora.happyshop.di.scopes.ProductActivityScope;
import sephora.happyshop.rx.DataObserver;

/**
 * Created by fadel on 25/8/17.
 */

@ProductActivityScope
@Component(modules = ProductActivityModule.class, dependencies = ApplicationComponent.class)
public interface ProductActivityComponent {
    ActivityProductBinding  activityProductBinding();
    ProductActivityViewModel productActivityViewModel();
    ApplicationComponent appComp();

    void inject(ProductActivity activity);

    void inject(DataObserver dataObserver);
}
