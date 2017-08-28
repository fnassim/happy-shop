package sephora.happyshop.mvvm.ViewModels;

/**
 * Created by fadel on 23/8/17.
 */

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
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
import sephora.happyshop.di.components.ApplicationComponent;
import sephora.happyshop.mvvm.Models.Products;
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.ui.Activities.ProductActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivityViewModel extends ViewModel implements Observer<Products> {
    @Inject
    protected BehaviorSubject<Products> productsListSubject;
    @Inject
    protected ObservableBoolean isLoading;
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private ApiService mApiService;

    public MainActivityViewModel() {
        MainActivity.getActivityComponent().inject(this);
        initEssentials();
        getProducts();

    }

    public BehaviorSubject<Products> getProductsListSubject() {
        return productsListSubject;
    }

    void initEssentials() {
        ApplicationComponent mAppCompontent = HappyShopApplication.getApp().getApplicationComponent();
        mContext = mAppCompontent.getContext();
        mApiService = mAppCompontent.getApiService();
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    // Fetches the Products data from the API
    public void getProducts() {
        mApiService.getProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(this);
    }

    // Function called by the SwipeRefreshLatout in order to refresh the RecyclerView data.
    public void refreshProducts() {
        getProducts();
        isLoading.set(true);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        isLoading.set(true);
    }

    @Override
    public void onNext(@NonNull Products apiData) {
        productsListSubject.onNext(apiData);
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

    public void onItemClick(Integer id) {
        Intent intent = new Intent(mContext, ProductActivity.class);
        intent.putExtra(EXTRA_MESSAGE, id.toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
