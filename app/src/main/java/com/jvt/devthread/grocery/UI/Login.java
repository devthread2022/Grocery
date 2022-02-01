package com.jvt.devthread.grocery.UI;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.grocery.HomeActivity;
import com.jvt.devthread.grocery.R;
import com.jvt.devthread.grocery.databinding.FragmentLoginBinding;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends Fragment {
    private FragmentLoginBinding binding;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    public static Fragment activeFragment;
    FirebaseAuth firebaseAuth;
    String email, password,mobile;
    private static final int RC_SIGN_IN = 234;
    CallbackManager callbackManager;
    String emailRegEx;
    DatabaseReference databaseReference;
    Dialog toastDialog;
    TextView toastMessage;

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;
    String pattern1 = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
    Matcher m;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentLoginBinding.inflate(inflater,container,false);
       View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        binding.regBtn.setOnClickListener(view1 -> {
            Fragment fragment = new SignUp();
            loadFragment(fragment,"SignUp");
        });
        binding.login.setOnClickListener(this::loginUser);
        binding.forgotPassword.setOnClickListener(view1 -> {
            Fragment forgot = new ForgotPassword();
            loadFragment(forgot,"ResetPassword");
        });
       return view;
    }

    private void loginUser(View view) {
        email = binding.email.getText().toString();
        Pattern pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(email);
        password = binding.password.getText().toString();
        Pattern r = Pattern.compile(pattern1);
        m = r.matcher(email);
        if (email.isEmpty()){
            binding.email.setError("Enter registered email id/Mobile");
            binding.email.requestFocus();
        }else if (password.isEmpty()){
            binding.password.setError("Enter Password");
            binding.password.requestFocus();
        }else if (matcher.find()){
            authenticateUser(email,password);
        }else if (m.find() && email.length() == 10){
            binding.login.setVisibility(View.INVISIBLE);
            binding.loading.setVisibility(View.VISIBLE);
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("UsersEmail").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(email)){
                        email = snapshot.child(email).getValue().toString();
                        authenticateUser(email,password);
                    }else {
                        Toast.makeText(getContext(), "User does not exists. Please check", Toast.LENGTH_SHORT).show();
                        binding.login.setVisibility(View.VISIBLE);
                        binding.loading.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if (!m.find() || !matcher.find()){
            Toast.makeText(getContext(), "Please check email or mobile.", Toast.LENGTH_SHORT).show();
        }
    }

    private void authenticateUser(String email, String password) {
        binding.login.setVisibility(View.INVISIBLE);
        binding.loading.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                binding.login.setVisibility(View.VISIBLE);
                binding.loading.setVisibility(View.INVISIBLE);
            }else {
                if (firebaseAuth.getCurrentUser() != null){
                    binding.login.setVisibility(View.VISIBLE);
                    binding.loading.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    Toast.makeText(getContext(), "Logged in successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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