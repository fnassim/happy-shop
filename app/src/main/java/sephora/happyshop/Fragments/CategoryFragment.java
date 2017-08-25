package sephora.happyshop.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import sephora.happyshop.MVVM.Models.Products;
import sephora.happyshop.MVVM.ViewModels.MainActivityViewModel;
import sephora.happyshop.R;
import sephora.happyshop.databinding.FragmentCategoryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements Observer<Products> {
    private MainActivityViewModel mainActivityViewModel;
    private FragmentCategoryBinding fragmentCategoryBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getProductsList().subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Sets the fragment_current View using the DataBindingUtil class.
        fragmentCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        fragmentCategoryBinding.setViewModel(mainActivityViewModel);

        return fragmentCategoryBinding.getRoot();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Products products) {
        Log.d("Products", products.getProducts().get(0).getName());
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
