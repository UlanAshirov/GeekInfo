package com.joma.geekinfo.aut;

import static com.google.android.material.tabs.TabLayout.GRAVITY_FILL;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.joma.geekinfo.R;
import com.joma.geekinfo.databinding.FragmentBlankBinding;


public class BlankFragment extends Fragment {
    private FragmentBlankBinding binding;
    private PagerAutAdapter pagerAutAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater);
        statusBarChange();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initListener() {
        pagerAutAdapter = new PagerAutAdapter(getChildFragmentManager());
        binding.viewPagerMain.setAdapter(pagerAutAdapter);
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Авторизация"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Регистрация"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayout.setTabTextColors(Color.parseColor("#FFFFFFFF"),Color.parseColor("#FFFFFFFF"));
        binding.viewPagerMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPagerMain.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void statusBarChange() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#4c5272"));
        }
    }
}