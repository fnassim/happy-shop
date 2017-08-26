package sephora.happyshop.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import sephora.happyshop.MVVM.ViewModels.ProductActivityViewModel;
import sephora.happyshop.R;
import sephora.happyshop.application.HappyShopApplication;
import sephora.happyshop.databinding.ActivityProductBinding;
import sephora.happyshop.di.components.DaggerProductActivityComponent;
import sephora.happyshop.di.components.ProductActivityComponent;
import sephora.happyshop.di.modules.ProductActivityModule;

public class ProductActivity extends AppCompatActivity {
    private static ProductActivityComponent mComponent;
    @Inject
    protected ActivityProductBinding productActivityBinding;
    @Inject
    protected ProductActivityViewModel productActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mComponent = DaggerProductActivityComponent.builder()
                .productActivityModule(new ProductActivityModule(this))
                .applicationComponent(HappyShopApplication.getApp().getApplicationComponent())
                .build();
        mComponent.inject(this);
        productActivityBinding.setViewModel(productActivityViewModel);
    }

    public static ProductActivityComponent getComponent() {
        return mComponent;
    }
}
