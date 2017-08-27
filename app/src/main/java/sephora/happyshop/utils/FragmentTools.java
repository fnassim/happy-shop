package sephora.happyshop.utils;

import android.os.Bundle;

import sephora.happyshop.ui.Fragments.CategoryFragment;

/**
 * Created by fadel on 26/8/17.
 */

public class FragmentTools {
    public static CategoryFragment createCategoryFragment(String category) {
        Bundle b = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        b.putString("category", category);

        fragment.setArguments(b);
        return fragment;
    }
}
