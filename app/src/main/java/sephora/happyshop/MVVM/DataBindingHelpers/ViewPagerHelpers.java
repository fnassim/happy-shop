package sephora.happyshop.mvvm.DataBindingHelpers;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import sephora.happyshop.ui.Adapters.ViewPagerAdapter;
import sephora.happyshop.constants.CategoryConstants;
import sephora.happyshop.utils.FragmentTools;

// This helper sets the viewpager internal data.
public class ViewPagerHelpers {
    @BindingAdapter({"pager"})
    public static void setTabLayout(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter({"viewPagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, FragmentManager fragmentManager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        adapter.addFragment(FragmentTools.createCategoryFragment(CategoryConstants.MAKE_UP), CategoryConstants.MAKE_UP);
        adapter.addFragment(FragmentTools.createCategoryFragment(CategoryConstants.SKIN_CARE), CategoryConstants.SKIN_CARE);
        adapter.addFragment(FragmentTools.createCategoryFragment(CategoryConstants.BATH), CategoryConstants.BATH);
        adapter.addFragment(FragmentTools.createCategoryFragment(CategoryConstants.TOOLS), CategoryConstants.TOOLS);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
    }
}
