package com.jvt.devthread.grocery.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.grocery.Models.UserInfoModel;
import com.jvt.devthread.grocery.Models.WalletModel;
import com.jvt.devthread.grocery.R;
import com.jvt.devthread.grocery.databinding.FragmentSignUpBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUp extends Fragment {
    private FragmentSignUpBinding binding;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    public static Fragment activeFragment;
    double walletBalance;
    GoogleSignInClient mGoogleSignInClient;
    String uid, name, email, mobile, username, address, pic, enteredReferral, userReferral, password,UID;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference12,databaseReference10,databaseReference11,databaseReference9,databaseReference,databaseReference2,databaseReference3,
            databaseReference4,databaseReference5,databaseReference6,databaseReference7,databaseReference8;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        databaseReference3 = FirebaseDatabase.getInstance().getReference();
        databaseReference4 = FirebaseDatabase.getInstance().getReference();
        databaseReference5 = FirebaseDatabase.getInstance().getReference();
        databaseReference6 = FirebaseDatabase.getInstance().getReference();
        databaseReference7 = FirebaseDatabase.getInstance().getReference();
        databaseReference8 = FirebaseDatabase.getInstance().getReference();
        databaseReference9 = FirebaseDatabase.getInstance().getReference();
        databaseReference10 = FirebaseDatabase.getInstance().getReference();
        databaseReference11 = FirebaseDatabase.getInstance().getReference();
        databaseReference12 = FirebaseDatabase.getInstance().getReference();
        binding.login.setOnClickListener(view1 -> {
            Fragment fragment = new Login();
            loadFragment(fragment,"Login");
        });
        firebaseAuth = FirebaseAuth.getInstance();
        binding.register.setOnClickListener(view1 -> {
            register();
        });
        return view;
    }

    private void register() {
        email = binding.email.getText().toString();
        password = binding.password.getText().toString();
        mobile = binding.mobile.getText().toString();
        name = binding.name.getText().toString();
        enteredReferral = binding.referral.getText().toString();
        username = binding.nickName.getText().toString();
        if (name.isEmpty()){
            binding.name.setError("Enter Name");
            binding.name.requestFocus();
        }else if (username.isEmpty()){
            binding.nickName.setError("Enter username");
            binding.nickName.requestFocus();
        }
        else if (mobile.isEmpty()){
            binding.mobile.setError("Enter Mobile Number");
            binding.mobile.requestFocus();
        }else if (email.isEmpty()){
            binding.email.setError("Enter Email Id");
            binding.email.requestFocus();
        }else if (password.isEmpty()){
            binding.password.setError("Enter Password");
            binding.password.requestFocus();
        }else if (password.length() < 6){
            Toast.makeText(getContext(), "Password must be atleast of 6 characters.", Toast.LENGTH_SHORT).show();
            binding.password.requestFocus();
        }else {
            Random random = new Random();
            int referralCode = random.nextInt(1000000);
            userReferral = "GRO"+referralCode;
            if (enteredReferral.equals("")){
                enteredReferral = "GRONEW2021";
            }else if(!enteredReferral.equals("")){
                enteredReferral = binding.referral.getText().toString();
            }
            signUpUser(userReferral,name,email,password,mobile,enteredReferral,username);
        }
    }

    private void signUpUser(String userReferral, String name, String email, String password, String mobile, String enteredReferral, String username) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                String userEmailId = email;
                databaseReference6.child("UsersEmail").child(mobile).setValue(userEmailId);
                databaseReference7.child("UserNickNames").child(username).setValue(username);

                UID = firebaseAuth.getCurrentUser().getUid();

                databaseReference9.child("UserReferralId").child(userReferral).setValue(UID);
                uid = UID;
                pic = "";
                address = "";
                UserInfoModel userInfoModel = new UserInfoModel(uid, name, email, mobile, username, address, pic, enteredReferral, userReferral, password);
                databaseReference.child("Users").child(UID).child("UserInfo").setValue(userInfoModel).addOnSuccessListener(aVoid -> {
                });
                databaseReference10.child("UserReferralId").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(enteredReferral)){
                            String id = snapshot.child(enteredReferral).getValue().toString();
                            walletBalance = 71;
                            WalletModel walletModel = new WalletModel(walletBalance);
                            databaseReference4.child("Users").child(UID).child("Wallet").setValue(walletModel).addOnSuccessListener(aVoid -> {
                            });
                            databaseReference11.child("Users").child(id).child("Wallet").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()){
                                        long bBal = Long.parseLong(snapshot.child("bonusBalance").getValue().toString());
                                        long creditBonus = bBal+20;
                                        binding.bonus.setText(String.valueOf(creditBonus));
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                                }else {
                                    walletBalance = 51;
                                    WalletModel walletModel = new WalletModel(walletBalance);
                                    databaseReference4.child("Users").child(UID).child("Wallet").setValue(walletModel).addOnSuccessListener(aVoid -> {
                                });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(getContext(), "Account registered successfully. Please login..", Toast.LENGTH_SHORT).show();
                Fragment login = new Login();
                loadFragment(login,"Login");
            }else {
                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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