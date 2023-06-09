package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class InventoryList extends AppCompatActivity {

    ListView invList;
    static String inventar[] = {
            "Nudeln", "Mozarella", "Wodka", "Pierogi"
    };

    //ArrayList<String> inventar = getIntent().getStringArrayListExtra("inventar");


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        //Menu-Bar
        bottomNavigationView = findViewById(R.id.b_inventory);
        bottomNavigationView.setSelectedItemId(R.id.b_inventory);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.b_shopping) {
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


        setContentView(R.layout.activity_inventory_list);
        invList = findViewById(R.id.listInventar);
        // inventar = new ArrayList<>();
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, inventar);
        invList.setAdapter(arr);


        invList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                // Entferne das Element aus der Liste

                // inventar.remove(position); // gehört zu ArrayList
                //löschen von Array namens inventar
                int positionToDelete = position;
                for (int i = positionToDelete; i < inventar.length - 1; i++) {
                    inventar[i] = inventar[i + 1];
                }
                inventar = Arrays.copyOf(inventar, inventar.length - 1);
                // Aktualisiere die ListView
                arr.notifyDataSetChanged();

                return true;
            }
        });

    }

}