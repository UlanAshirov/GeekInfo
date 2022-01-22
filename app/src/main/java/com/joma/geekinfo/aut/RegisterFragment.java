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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.joma.geekinfo.R;
import com.joma.geekinfo.databinding.FragmentRegisterBinding;

import java.util.concurrent.TimeUnit;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private NavController controller;
    private FirebaseAuth auth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);
        auth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    private void getListener() {
        binding.txtSendMessageRegister.setOnClickListener(view -> {
            String phoneNumber = binding.editPhoneReg.getText().toString();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,
                    60,
                    TimeUnit.SECONDS,
                    requireActivity(),
                    callbacks);
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s,
                                   @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mVerificationId = s;
//                mToken = forceResendingToken;
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
        };
    }

    private void signWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).
                addOnCompleteListener(requireActivity(),
                        task -> {
                            if (task.isSuccessful()) {
                                controller.navigate(R.id.chatFragment);
                                controller.navigateUp();
                            }else {
                                Toast.makeText(requireContext(), "Пошел на хуй", Toast.LENGTH_SHORT).show();
                            }
                        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        getListener();
    }
}