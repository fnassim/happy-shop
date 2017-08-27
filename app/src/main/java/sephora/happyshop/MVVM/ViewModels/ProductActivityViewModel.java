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
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.api.ApiService;
import sephora.happyshop.mvvm.Models.Product;
import sephora.happyshop.mvvm.Models.ProductObject;
import sephora.happyshop.application.HappyShopApplication;
import sephora.happyshop.databinding.ActivityMainBinding;
import sephora.happyshop.di.components.ApplicationComponent;
import sephora.happyshop.rx.DataObserver;

/**
 * Created by fadel on 26/8/17.
 */

public class ProductActivityViewModel extends ViewModel implements Observer<ProductObject> {
    protected ApiService mService;
    protected SharedPreferences.Editor prefsEditor;
    protected SharedPreferences sharedPreferences;

    @Inject
    protected ActivityMainBinding mActivityMainBinding;

    private ApplicationComponent mAppCompontent;
    private String mProductId;
    private BehaviorSubject<Product> mProductSubject;
    private ObservableBoolean isLoading;

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setmProductId(String productId) {
        mProductId = productId;
        getProductData();
    }

    public ProductActivityViewModel() {
        mAppCompontent = HappyShopApplication.getApp().getApplicationComponent();
        sharedPreferences = mAppCompontent.sharedPrefs();
        prefsEditor = mAppCompontent.editor();
        mService = mAppCompontent.getApiService();

        MainActivity.getActivityComponent().inject(this);

        mProductSubject = BehaviorSubject.create();
        isLoading = new ObservableBoolean(false);
        mProductSubject
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DataObserver());
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
