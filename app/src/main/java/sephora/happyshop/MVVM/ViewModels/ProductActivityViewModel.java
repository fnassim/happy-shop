package sephora.happyshop.MVVM.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import sephora.happyshop.Api.ApiService;
import sephora.happyshop.MVVM.Models.Product;
import sephora.happyshop.MVVM.Models.ProductObject;
import sephora.happyshop.application.HappyShopApplication;
import sephora.happyshop.rx.Observers.DataObserver;

/**
 * Created by fadel on 26/8/17.
 */

public class ProductActivityViewModel extends ViewModel implements Observer<ProductObject> {
    @Inject
    protected ApiService mService;
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
        HappyShopApplication.getApp().getApplicationComponent().inject(this);
        mProductSubject = BehaviorSubject.create();
        isLoading = new ObservableBoolean(false);
        mProductSubject
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DataObserver(this));
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
}
