package sephora.happyshop.MVVM.DataBindingHelpers;

import android.databinding.BindingAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import sephora.happyshop.Adapters.ViewPagerAdapter;
import sephora.happyshop.Fragments.CategoryFragment;

// This helper sets the viewpager internal data.
public class ViewPagerHelpers {
    @BindingAdapter({"pager"})
    public static void setTabLayout(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter({"viewPagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, FragmentManager fragmentManager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(new CategoryFragment(), "Makeup");
        adapter.addFragment(new CategoryFragment(), "Skin Care");
        adapter.addFragment(new CategoryFragment(), "Hair");
        adapter.addFragment(new CategoryFragment(), "Tools & Brushes");
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
    }
}
