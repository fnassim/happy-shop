package sephora.happyshop.MVVM.ViewModels;

/**
 * Created by fadel on 23/8/17.
 */

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.Api.ApiService;
import sephora.happyshop.MVVM.Models.Products;
import sephora.happyshop.application.HappyShopApplication;

public class MainActivityViewModel extends ViewModel implements Observer<Products> {
    private Observable<Products> productsListObservable;
    private BehaviorSubject<Products> productsListSubject;
    private ObservableBoolean isLoading;

    @Inject
    protected ApiService mApiService;

    public MainActivityViewModel() {
        productsListSubject = BehaviorSubject.create();
        productsListObservable = this.productsListSubject;
        isLoading = new ObservableBoolean(false);
        HappyShopApplication.getApp().getApplicationComponent().inject(this);
        getProducts();
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public Observable<Products> getProductsList() {
        return productsListObservable;
    }

    // Fetches the Products data from the API
    public void getProducts() {
        mApiService.getProducts().observeOn(AndroidSchedulers.mainThread())
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
}
