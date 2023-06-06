package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;

public class InventoryList extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        //Menu-Bar
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.bottonnav);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                                                                     @Override
       public boolean onNavigationItemSelected(@NonNull MenuItem item) {

         if (item.getItemId() == R.id.shopping) {
             startActivity(new Intent(getApplicationContext(), ShoppingList.class));
             overridePendingTransition(0,0);
             return true;
         } else if (item.getItemId() == R.id.bottonnav){
             startActivity(new Intent(getApplicationContext(), Favorites.class));
             overridePendingTransition(0,0);
             return true;
         } else
             return false;
                                                                     }
        }
        );
    }
}