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
        binding.btnEnter.setEnabled(false);
        statusBarChange();
        return binding.getRoot();
    }

    private void statusBarChange() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#c0c0c0"));
        }
    }

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
                if(binding.etNumberPhone.getText().toString().length() > 0 && binding.etPassword.getText().toString().length() > 0){
                    binding.btnEnter.setBackgroundResource(R.drawable.circle_button_blue);
                    binding.btnEnter.setEnabled(true);
                } else {
                    binding.btnEnter.setBackgroundResource(R.drawable.circle_button_default);
                    binding.btnEnter.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.etPassword.addTextChangedListener(textWatcher);
        binding.etNumberPhone.addTextChangedListener(textWatcher);
    }

    private void initListeners() {
        binding.btnRegister.setOnClickListener(view -> {
            controller.navigate(R.id.registerFragment);
        });
        binding.btnEnter.setOnClickListener(view -> {
            controller.navigate(R.id.chatFragment);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(),0);
        });
    }
}