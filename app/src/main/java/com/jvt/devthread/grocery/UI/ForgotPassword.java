package com.jvt.devthread.grocery.UI;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.jvt.devthread.grocery.R;
import com.jvt.devthread.grocery.databinding.FragmentForgotPasswordBinding;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForgotPassword extends Fragment {
    private FragmentForgotPasswordBinding binding;
    Dialog toastDialog;
    TextView toastMessage;
    public static Fragment activeFragment;
    FirebaseAuth firebaseAuth;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        binding.sendEmail.setOnClickListener(view1 -> {
            String email = binding.email.getText().toString();
            if (email.isEmpty()){
                binding.email.requestFocus();
                binding.email.setError("Enter your email!");
            }else {
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(), "Reset link has been sent to \""+email+"\n please check and follow the instructions.", Toast.LENGTH_SHORT).show();
                                Fragment login = new Login();
                                loadFragment(login,"Login");
                            }else {
                                Toast.makeText(getContext(), "Failed to send reset password link. Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment, String tag) {
        executorService.execute(() -> {
            if (fragment != null) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag).commit();

            }
            handler.post(() -> {
                activeFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            });
        });
    }
}