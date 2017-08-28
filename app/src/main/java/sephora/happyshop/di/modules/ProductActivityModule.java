package sephora.happyshop.di.modules;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.R;
import sephora.happyshop.databinding.ActivityProductBinding;
import sephora.happyshop.di.scopes.ProductActivityScope;
import sephora.happyshop.mvvm.Models.Product;
import sephora.happyshop.mvvm.ViewModels.ProductActivityViewModel;
import sephora.happyshop.rx.DataObserver;
import sephora.happyshop.ui.Activities.ProductActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by fadel on 25/8/17.
 */

@Module
public class ProductActivityModule {
    private ProductActivity mActivity;

    public ProductActivityModule(ProductActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ProductActivityScope
    ActivityProductBinding productActivityBinding() {
        return DataBindingUtil.setContentView(mActivity, R.layout.activity_product);
    }

    @Provides
    @ProductActivityScope
    ProductActivityViewModel productActivityViewModel() {
        ProductActivityViewModel viewModel = ViewModelProviders.of(mActivity).get(ProductActivityViewModel.class);
        viewModel.setmProductId(mActivity.getIntent().getExtras().getString(EXTRA_MESSAGE));
        return viewModel;
    }

    @Provides
    @ProductActivityScope
    ObservableBoolean observableBoolean() {
        return new ObservableBoolean(false);
    }

    @Provides
    @ProductActivityScope
    BehaviorSubject<Product> behaviorSubjects() {
        return BehaviorSubject.create();
    }

    @Provides
    @ProductActivityScope
    DataObserver observer() {
        return new DataObserver();
    }
}
