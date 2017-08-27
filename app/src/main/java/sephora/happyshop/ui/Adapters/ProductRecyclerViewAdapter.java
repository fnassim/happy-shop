package sephora.happyshop.ui.Adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import sephora.happyshop.R;
import sephora.happyshop.databinding.ProductItemBinding;
import sephora.happyshop.mvvm.Models.Product;
import sephora.happyshop.mvvm.ViewModels.MainActivityViewModel;
import sephora.happyshop.ui.Activities.MainActivity;
import sephora.happyshop.utils.UnderscoreTools;

/**
 * Created by fadel on 25/8/17.
 */

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    @Inject
    protected MainActivityViewModel mViewModel;
    private List<Product> mProductsList;
    private String mCategory;

    public ProductRecyclerViewAdapter() {
        MainActivity.getActivityComponent().inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProductItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.product_item, parent, false);

        viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mProductsList != null)
            holder.bind(mProductsList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (mProductsList != null)
            return mProductsList.size();
        return -1;
    }

    public void updateList(List<Product> newDataList, String category) {
        mProductsList = UnderscoreTools.filterProducts(newDataList, category);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ProductItemBinding mBinding;

        public ViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Product productsListItem) {
            mBinding.setItemData(productsListItem);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }
    }
}