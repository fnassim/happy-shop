package sephora.happyshop.di.modules;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;

import dagger.Module;
import dagger.Provides;
import sephora.happyshop.Activities.MainActivity;
import sephora.happyshop.Activities.ProductActivity;
import sephora.happyshop.MVVM.Models.Product;
import sephora.happyshop.MVVM.ViewModels.MainActivityViewModel;
import sephora.happyshop.MVVM.ViewModels.ProductActivityViewModel;
import sephora.happyshop.R;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.databinding.ActivityProductBinding;
import sephora.happyshop.di.scopes.MainActivityScope;
import sephora.happyshop.di.scopes.ProductActivityScope;

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
}
