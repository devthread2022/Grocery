package com.jvt.devthread.grocery.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.grocery.R;
import com.jvt.devthread.grocery.databinding.FragmentProfileBinding;
import com.jvt.devthread.grocery.databinding.FragmentProfileDetailsBinding;

public class ProfileDetails extends Fragment {
    private FragmentProfileDetailsBinding binding;
    String uid,name, email, mobile, username, address, pic, enteredReferral, userReferral;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Users").child(uid).child("UserInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    name = snapshot.child("name").getValue().toString();
                    email = snapshot.child("email").getValue().toString();
                    mobile = snapshot.child("mobile").getValue().toString();
                    username = snapshot.child("username").getValue().toString();
                    address = snapshot.child("address").getValue().toString();
                    pic = snapshot.child("pic").getValue().toString();
                    enteredReferral = snapshot.child("enteredReferral").getValue().toString();
                    userReferral = snapshot.child("userReferral").getValue().toString();
                    binding.name.setText(name);
                    binding.username.setText(username);
                    binding.email.setText(email);
                    binding.mobile.setText(mobile);
                    binding.address.setText(address);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}