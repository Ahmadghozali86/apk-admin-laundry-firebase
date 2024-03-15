package com.example.admin_laundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Layanan layanan;
    Riwayat riwayat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layanan =  new Layanan();
        riwayat = new Riwayat();
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_layanan){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,layanan).commit();
                }
                if(item.getItemId()==R.id.menu_riwayat){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,riwayat).commit();
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.menu_layanan);
    }
}