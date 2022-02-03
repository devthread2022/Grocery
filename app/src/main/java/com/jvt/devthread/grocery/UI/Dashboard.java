package com.jvt.devthread.grocery.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.grocery.Adapters.BannerAdapter;
import com.jvt.devthread.grocery.Adapters.CategoryAdapter;
import com.jvt.devthread.grocery.Adapters.DashboardCategoryAdapter;
import com.jvt.devthread.grocery.Adapters.ProductListAdapter;
import com.jvt.devthread.grocery.Models.BannerModel;
import com.jvt.devthread.grocery.Models.CategoryModel;
import com.jvt.devthread.grocery.Models.ProductListModel;
import com.jvt.devthread.grocery.R;
import com.jvt.devthread.grocery.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Fragment {
    private FragmentDashboardBinding binding;
    DatabaseReference databaseReference1, databaseReference2, databaseReference3, databaseReference4, databaseReference5;
    String uid,name, email, mobile, username, address, pic, enteredReferral, userReferral;
    FirebaseAuth firebaseAuth;
    private List<BannerModel> bannerModelList = new ArrayList<>();
    private List<CategoryModel> categoryModelList = new ArrayList<>();
    private List<ProductListModel> productListModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        databaseReference3 = FirebaseDatabase.getInstance().getReference();
        databaseReference4 = FirebaseDatabase.getInstance().getReference();
        databaseReference5 = FirebaseDatabase.getInstance().getReference();
        uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference4.child("Users").child(uid).child("UserInfo").addValueEventListener(new ValueEventListener() {
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
                        binding.name.setText("Hello, "+name+"!");
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.bannerRecycler.setHasFixedSize(false);
        binding.bannerRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.catRecycler.setHasFixedSize(false);
        binding.catRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.offersRecycler.setHasFixedSize(false);
        binding.offersRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.recentRecycler.setHasFixedSize(false);
        binding.recentRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        databaseReference1.child("Banner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    bannerModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        BannerModel bannerModel = dataSnapshot.getValue(BannerModel.class);
                        bannerModelList.add(bannerModel);
                    }
                    BannerAdapter bannerAdapter = new BannerAdapter(getContext(),bannerModelList);
                    binding.bannerRecycler.setAdapter(bannerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference2.child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    categoryModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        CategoryModel categoryModel = dataSnapshot.getValue(CategoryModel.class);
                        categoryModelList.add(categoryModel);
                    }
                    DashboardCategoryAdapter categoryAdapter = new DashboardCategoryAdapter(getContext(),categoryModelList);
                    binding.catRecycler.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference3.child("RecentAdded").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    productListModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ProductListModel productListModel = dataSnapshot.getValue(ProductListModel.class);
                        productListModelList.add(productListModel);
                    }
                    ProductListAdapter productListAdapter = new ProductListAdapter(getContext(),productListModelList);
                    binding.recentRecycler.setAdapter(productListAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}