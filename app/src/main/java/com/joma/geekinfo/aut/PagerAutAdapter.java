package com.joma.geekinfo.aut;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAutAdapter extends FragmentPagerAdapter {
    public PagerAutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new AutFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
