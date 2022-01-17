package com.joma.geekinfo.aut;

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

import com.joma.geekinfo.R;
import com.joma.geekinfo.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private NavController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);
        binding.btnReg.setEnabled(false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        getListener();
        initListener();
    }

    private void initListener() {
        binding.btnReg.setOnClickListener(v -> {
            controller.navigate(R.id.chatFragment);
        });
    }

    private void getListener() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.editNameReg.getText().toString().length() > 0 && binding.editPhoneReg.getText().toString().length()>0 && binding.editPasswordReg.getText().toString().length()> 0){
                    binding.btnReg.setBackgroundResource(R.drawable.circle_button_click);
                    binding.btnReg.setEnabled(true);
                } else {
                    binding.btnReg.setBackgroundResource(R.drawable.circle_button_default);
                    binding.btnReg.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.editNameReg.addTextChangedListener(textWatcher);
        binding.editPasswordReg.addTextChangedListener(textWatcher);
        binding.editPhoneReg.addTextChangedListener(textWatcher);
    }
}