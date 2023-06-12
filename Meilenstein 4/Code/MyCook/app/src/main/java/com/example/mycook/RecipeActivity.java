package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecipeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        //Menu-Bar
        /*
        bottomNavigationView = findViewById(R.id.b_favorites);
        bottomNavigationView.setSelectedItemId(R.id.b_favorites);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.b_inventory) {
                    startActivity(new Intent(getApplicationContext(), InventoryList.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.b_shopping) {
                    startActivity(new Intent(getApplicationContext(), ShoppingList.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.b_favorites) {
                    startActivity(new Intent(getApplicationContext(), Favorites.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else
                    return false;
            }
        }
        );*/
    }
    }
