package com.example.libraryofpeers.view;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.libraryofpeers.R;

import Entities.User;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    protected static int CATALOG_FRAGMENT_POSITION = 0;
    protected static int FRIENDS_FRAGMENT_POSITION = 1;

    public static int[] DEFAULT_TAB_TITLES = new int[]{R.string.catalogTabTitle, R.string.friendsTabTitle};


    @StringRes
    public int[] TAB_TITLES;
    private final Context mContext;
    private final User user;
    private String query;

    public SectionsPagerAdapter(Context context, FragmentManager fm, User user, int[] tab_titles) {
        super(fm);
        mContext = context;
        this.user = user;
        this.TAB_TITLES = tab_titles;
        this.query = "";
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == CATALOG_FRAGMENT_POSITION) {
            return CatalogFragment.newInstance(user, query);
        } else if (position == FRIENDS_FRAGMENT_POSITION) {
            return CatalogFragment.newInstance(user, query);  // will change to FriendsFragment
        }
        else {
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}