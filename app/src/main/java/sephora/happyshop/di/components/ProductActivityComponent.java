package sephora.happyshop.di.components;


import android.databinding.ObservableBoolean;

import dagger.Component;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.databinding.ActivityProductBinding;
import sephora.happyshop.di.modules.ProductActivityModule;
import sephora.happyshop.di.scopes.ProductActivityScope;
import sephora.happyshop.mvvm.Models.Product;
import sephora.happyshop.mvvm.ViewModels.ProductActivityViewModel;
import sephora.happyshop.rx.DataObserver;
import sephora.happyshop.ui.Activities.ProductActivity;

/**
 * Created by fadel on 25/8/17.
 */

@ProductActivityScope
@Component(modules = ProductActivityModule.class, dependencies = ApplicationComponent.class)
public interface ProductActivityComponent {
    ActivityProductBinding  activityProductBinding();
    ProductActivityViewModel productActivityViewModel();
    ApplicationComponent appComp();

    ObservableBoolean observableBoolean();

    BehaviorSubject<Product> behaviourSubject();

    DataObserver observer();

    void inject(ProductActivity activity);
    void inject(DataObserver dataObserver);

    void inject(ProductActivityViewModel viewModel);
}
