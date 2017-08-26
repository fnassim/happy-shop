package sephora.happyshop.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import sephora.happyshop.Adapters.ProductRecyclerViewAdapter;
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
    private ProductRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getProductsList().subscribe(this);
        mAdapter = new ProductRecyclerViewAdapter(getArguments().getString("category"));
//        HappyShopApplication.getApp().getApplicationComponent().inject(this);
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
        mAdapter.updateList(products.getProducts());
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
