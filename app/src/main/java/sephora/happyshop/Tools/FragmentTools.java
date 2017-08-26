package sephora.happyshop.Tools;

import android.os.Bundle;

import sephora.happyshop.Fragments.CategoryFragment;

/**
 * Created by fadel on 26/8/17.
 */

public class FragmentTools {
    public static CategoryFragment createFragment(String category) {
        Bundle b = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        b.putString("category", category);

        fragment.setArguments(b);
        return fragment;
    }
}
