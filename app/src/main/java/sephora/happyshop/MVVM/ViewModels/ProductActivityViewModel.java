package sephora.happyshop.mvvm.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.api.ApiService;
import sephora.happyshop.application.HappyShopApplication;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.components.ApplicationComponent;
import sephora.happyshop.di.components.MainActivityComponent;
import sephora.happyshop.mvvm.Models.Product;
import sephora.happyshop.mvvm.Models.ProductObject;
import sephora.happyshop.rx.DataObserver;
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.ui.Activities.ProductActivity;

/**
 * Created by fadel on 26/8/17.
 */

public class ProductActivityViewModel extends ViewModel implements Observer<ProductObject> {
    @Inject
    protected BehaviorSubject<Product> mProductSubject;
    @Inject
    protected ObservableBoolean isLoading;
    @Inject
    protected DataObserver mObserver;
    private ApiService mService;
    private SharedPreferences.Editor prefsEditor;
    private SharedPreferences sharedPreferences;
    private ActivityMainBinding mActivityMainBinding;
    private String mProductId;

    public ProductActivityViewModel() {
        ProductActivity.getComponent().inject(this);
        initEssentials();
        initMainActivityBindings();
        mProductSubject
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(mObserver);
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setmProductId(String productId) {
        mProductId = productId;
        getProductData();
    }

    public void initMainActivityBindings() {
        MainActivityComponent mainActivityComponent = MainActivity.getActivityComponent();
        mActivityMainBinding = mainActivityComponent.activityMainBinding();
    }

    void initEssentials() {
        ApplicationComponent mAppCompontent = HappyShopApplication.getApp().getApplicationComponent();
        sharedPreferences = mAppCompontent.sharedPrefs();
        prefsEditor = mAppCompontent.editor();
        mService = mAppCompontent.getApiService();
    }

    void getProductData() {
        mService.getProduct(mProductId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        isLoading.set(true);
    }

    @Override
    public void onNext(@NonNull ProductObject product) {
        mProductSubject.onNext(product.getProduct());
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        isLoading.set(false);
    }

    @Override
    public void onComplete() {
        isLoading.set(false);
    }

    public void addToCart() {
        int cartValue = sharedPreferences.getInt("cartArticlesNb", 0);
        cartValue += 1;

        mActivityMainBinding.setCartCt(cartValue);
        mActivityMainBinding.executePendingBindings();
        prefsEditor.putInt("cartArticlesNb", cartValue);
        prefsEditor.commit();
    }
}
