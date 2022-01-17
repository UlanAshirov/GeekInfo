package com.joma.geekinfo.aut;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.joma.geekinfo.R;
import com.joma.geekinfo.databinding.FragmentAutBinding;

public class AutFragment extends Fragment {
    private FragmentAutBinding binding;
    private NavController controller;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAutBinding.inflate(inflater);
        binding.btnAut.setEnabled(false);
//        statusBarChange();
        return binding.getRoot();
    }

//    private void statusBarChange() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Window window = getActivity().getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.parseColor("#4c5272"));
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        initListeners();
        initViews();
    }

    private void initViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.edtUserName.getText().toString().length() > 0 && binding.edtPassword.getText().toString().length() > 0){
                    binding.btnAut.setBackgroundResource(R.drawable.circle_button_click);
                    binding.btnAut.setEnabled(true);
                } else {
                    binding.btnAut.setBackgroundResource(R.drawable.circle_button_default);
                    binding.btnAut.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.edtPassword.addTextChangedListener(textWatcher);
        binding.edtUserName.addTextChangedListener(textWatcher);
    }

    private void initListeners() {

    }
}