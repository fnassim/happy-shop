package sephora.happyshop.Adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import sephora.happyshop.MVVM.Models.Product;
import sephora.happyshop.R;
import sephora.happyshop.Tools.UnderscoreTools;
import sephora.happyshop.databinding.ProductItemBinding;

/**
 * Created by fadel on 25/8/17.
 */

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    private List<Product> mProductsList;
    private String mCategory;

    public ProductRecyclerViewAdapter(String category) {
        mCategory = category;
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

    public void updateList(List<Product> newDataList) {
        mProductsList = UnderscoreTools.filterProducts(newDataList, mCategory);
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
            mBinding.executePendingBindings();
        }
    }
}