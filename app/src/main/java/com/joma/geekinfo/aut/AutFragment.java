package com.joma.geekinfo.aut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.joma.geekinfo.R;
import com.joma.geekinfo.databinding.FragmentAutBinding;

import java.util.concurrent.TimeUnit;

public class AutFragment extends Fragment {
    private FragmentAutBinding binding;
    private NavController controller;
    private String verificationCodeBySystem;
    private PhoneAuthProvider.ForceResendingToken token;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAutBinding.inflate(inflater);
        // binding.btnAut.setEnabled(false);

        firebaseAuth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    private void sendVerificationCodeToUser(String phoneNumber) {
        /*PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .*/
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+996" + phoneNumber,
                60,
                TimeUnit.SECONDS,
                requireActivity(),
                mCallbacks);
    }

    private void verifiCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, code);
        signInTheUserByCredential(credential);
    }

    private void signInTheUserByCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                         controller.navigate(R.id.chatFragment);
                        Toast.makeText(requireContext(), "ЗАяюался алвфыповфыджлпо ", Toast.LENGTH_SHORT).show();

                    } else  {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        mCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCodeBySystem = s;
                        token = forceResendingToken;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code = phoneAuthCredential.getSmsCode();
                        if (code != null) {
                            Toast.makeText(requireContext(),"Пшелвафафвы",Toast.LENGTH_LONG).show();
                            verifiCode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }
                };
        //initViews();
        sendMessageRegister();
    }

    private void sendMessageRegister() {
        binding.btnAut.setOnClickListener(view -> {
            String phone = binding.edtUserName.getText().toString();
                Toast.makeText(requireContext(), "Баель", Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show();
        });
        binding.txtSendMessage.setOnClickListener(view -> {
            String phoneNumber = binding.edtUserName.getText().toString();
            Log.e("----------", phoneNumber);
            sendVerificationCodeToUser(phoneNumber);
        });
    }

    private void abobaListener() {
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
    }
    private void initViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.edtUserName.getText().toString().length() > 0 && binding.edtPassword.getText().toString().length() > 0) {
                    binding.btnAut.setBackgroundResource(R.drawable.circle_button_click);
                    //  binding.btnAut.setEnabled(true);
                } else {
                    binding.btnAut.setBackgroundResource(R.drawable.circle_button_default);
                    // binding.btnAut.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.edtPassword.addTextChangedListener(textWatcher);
        binding.edtUserName.addTextChangedListener(textWatcher);
    }
}