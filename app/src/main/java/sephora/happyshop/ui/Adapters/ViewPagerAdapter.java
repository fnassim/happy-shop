package sephora.happyshop.ui.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList;
    private final List<String> mFragmentTitleList;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);

        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
    }

    // Returns a Fragment attached to the ViewPager at a given position.
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    // Returns the number of Fragments attached to the ViewPager.
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    // Adds a Fragment to the ViewPager.
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    // Returns the title of the page at a given position.
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}