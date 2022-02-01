package com.jvt.devthread.grocery.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvt.devthread.grocery.R;
import com.jvt.devthread.grocery.databinding.FragmentSupportBinding;

public class Support extends Fragment {
    private FragmentSupportBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSupportBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}