package sephora.happyshop.ui.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sephora.happyshop.R;
import sephora.happyshop.databinding.FragmentCategoryBinding;
import sephora.happyshop.mvvm.Models.Products;
import sephora.happyshop.mvvm.ViewModels.MainActivityViewModel;
import sephora.happyshop.rx.DisposableUtil;
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.ui.Adapters.ProductRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements Observer<Products> {
    @Inject
    protected MainActivityViewModel mainActivityViewModel;
    private FragmentCategoryBinding fragmentCategoryBinding;
    private ProductRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ProductRecyclerViewAdapter();
        MainActivity.getActivityComponent().inject(this);
        mainActivityViewModel.getProductsListSubject()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        fragmentCategoryBinding.setViewModel(mainActivityViewModel);
        initRecyclerViewInternals();
        return fragmentCategoryBinding.getRoot();
    }

    // This function sets the necessary Adapter and LayoutManager to the CurrentAuctions RecyclerView
    public void initRecyclerViewInternals() {
        fragmentCategoryBinding.productsRecycler.setAdapter(mAdapter);
        fragmentCategoryBinding.productsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Products products) {
        mAdapter.updateList(products.getProducts(), getArguments().getString("category"));
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onPause() {
        super.onPause();
        DisposableUtil.dispose();
    }

    @Override
    public void onStop() {
        super.onStop();
        DisposableUtil.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DisposableUtil.dispose();
    }
}
