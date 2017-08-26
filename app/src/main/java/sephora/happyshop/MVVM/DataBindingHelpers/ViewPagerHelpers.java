package sephora.happyshop.MVVM.DataBindingHelpers;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import sephora.happyshop.Adapters.ViewPagerAdapter;
import sephora.happyshop.Constants.CategoryConstants;
import sephora.happyshop.Fragments.CategoryFragment;
import sephora.happyshop.Tools.FragmentTools;

// This helper sets the viewpager internal data.
public class ViewPagerHelpers {
    @BindingAdapter({"pager"})
    public static void setTabLayout(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter({"viewPagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, FragmentManager fragmentManager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        adapter.addFragment(FragmentTools.createFragment(CategoryConstants.MAKE_UP), CategoryConstants.MAKE_UP);
        adapter.addFragment(FragmentTools.createFragment(CategoryConstants.SKIN_CARE), CategoryConstants.SKIN_CARE);
        adapter.addFragment(FragmentTools.createFragment(CategoryConstants.BATH), CategoryConstants.BATH);
        adapter.addFragment(FragmentTools.createFragment(CategoryConstants.TOOLS), CategoryConstants.TOOLS);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
    }
}
