package com.example.mycook;
import java.io.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.os.Bundle;

public class Favorites extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        //Menu-Bar
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.favorites);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.inventory) {
                    startActivity(new Intent(getApplicationContext(), InventoryList.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if (item.getItemId() == R.id.shopping){
                    startActivity(new Intent(getApplicationContext(), ShoppingList.class));
                    overridePendingTransition(0,0);
                    return true;
                } else
                    return false;
            }
        }
        );
    }
}


