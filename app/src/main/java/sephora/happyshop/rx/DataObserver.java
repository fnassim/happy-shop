package sephora.happyshop.rx;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import sephora.happyshop.databinding.ActivityProductBinding;
import sephora.happyshop.mvvm.Models.Product;
import sephora.happyshop.ui.Activities.ProductActivity;

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
