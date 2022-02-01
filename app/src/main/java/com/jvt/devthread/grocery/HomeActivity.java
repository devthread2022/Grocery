package com.jvt.devthread.grocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.jvt.devthread.grocery.UI.Dashboard;
import com.jvt.devthread.grocery.databinding.ActivityHomeBinding;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    final FragmentManager fm = getSupportFragmentManager();
    public static Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Fragment dashboard = new Dashboard();
        loadFragment(dashboard,"Dashboard");
        setContentView(view);
    }
    @SuppressLint("StaticFieldLeak")
    private void loadFragment(Fragment fragment, String tag)
    {
        executorService.execute(() ->{
            if (fragment!=null)
            {
                fm.beginTransaction().replace(R.id.container, fragment,tag).addToBackStack(tag).commitAllowingStateLoss();
            }
            handler.post(() ->{
                activeFragment=fm.findFragmentById(R.id.container);
            });
        });
    }
    public String convertValueInIndianCurrency(Long amount)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("hi", "IN"));
        return formatter.format(amount);
    }
}