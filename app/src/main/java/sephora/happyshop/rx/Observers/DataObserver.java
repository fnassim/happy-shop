package sephora.happyshop.rx.Observers;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import sephora.happyshop.Activities.ProductActivity;
import sephora.happyshop.MVVM.Models.Product;
import sephora.happyshop.MVVM.ViewModels.ProductActivityViewModel;
import sephora.happyshop.databinding.ActivityProductBinding;

/**
 * Created by fadel on 26/8/17.
 */

public class DataObserver implements Observer<Product> {
    @Inject
    ActivityProductBinding productBinding;


    public DataObserver() {
        ProductActivity.getComponent().inject(this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Product product) {
        productBinding.setProduct(product);
        productBinding.executePendingBindings();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
