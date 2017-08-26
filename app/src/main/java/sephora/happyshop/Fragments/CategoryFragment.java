package sephora.happyshop.Fragments;


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
import sephora.happyshop.Activities.MainActivity;
import sephora.happyshop.Adapters.ProductRecyclerViewAdapter;
import sephora.happyshop.MVVM.Models.Products;
import sephora.happyshop.MVVM.ViewModels.MainActivityViewModel;
import sephora.happyshop.R;
import sephora.happyshop.databinding.FragmentCategoryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements Observer<Products> {
    private FragmentCategoryBinding fragmentCategoryBinding;
    private ProductRecyclerViewAdapter mAdapter;


    @Inject
    protected MainActivityViewModel mainActivityViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ProductRecyclerViewAdapter();
        MainActivity.getActivityComponent().inject(this);
        mainActivityViewModel.getProductsList().subscribe(this);
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
}
