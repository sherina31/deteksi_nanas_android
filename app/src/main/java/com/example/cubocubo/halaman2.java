package com.example.cubocubo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cubocubo.databinding.ActivityHalaman2Binding;
import com.example.cubocubo.databinding.ActivityHalaman2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class halaman2 extends AppCompatActivity {

    ActivityHalaman2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHalaman2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottonnav.setOnItemReselectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.deteksi:
                    replaceFragment(new DeteksiFragment());
                    break;
                case R.id.stok:
                    replaceFragment(new StokFragment());
                    break;
                case R.id.informasi:
                    replaceFragment(new InformasiFragment());
                    break;
                case R.id.keluar:
                    replaceFragment(new AkunFragment());
                    break;

            }

            return;

        });


    }

    private void replaceFragment (Fragment fragment ){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();


    }
}