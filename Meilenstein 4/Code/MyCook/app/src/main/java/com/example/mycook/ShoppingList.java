package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.View.OnClickListener;
import android.view.View.OnClickListener;

public class ShoppingList extends AppCompatActivity {


    EditText eingabeText;
    Button addButton;
    ListView shoppingListView;
    ArrayAdapter <String> adapterShopping;
    ArrayList<String> inventar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //Menu-Bar
        bottomNavigationView = findViewById(R.id.b_shopping);
        bottomNavigationView.setSelectedItemId(R.id.b_shopping);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.b_inventory) {
                    startActivity(new Intent(getApplicationContext(), InventoryList.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if (item.getItemId() == R.id.b_favorites){
                    startActivity(new Intent(getApplicationContext(), Favorites.class));
                    overridePendingTransition(0,0);
                    return true;
                } else
                    return false;
            }
        }
        );

        eingabeText = findViewById(R.id.eingabefeldShoppinglist);
        addButton = findViewById(R.id.buttonAddShoppinglist);
        shoppingListView = findViewById(R.id.listviewShoppinglist);

        inventar = new ArrayList<>();
        adapterShopping = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inventar);
        shoppingListView.setAdapter(adapterShopping);

        Intent intent = new Intent(this, InventoryList.class);
        intent.putStringArrayListExtra("inventar", inventar);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Füge das eingegebene Element zur Liste hinzu
                String newItem = eingabeText.getText().toString();
                inventar.add(newItem);
                // Aktualisiere den Adapter
                adapterShopping.notifyDataSetChanged();
                // Setze das Eingabefeld zurück
                eingabeText.setText("WarenName");


            }
        });

        shoppingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Entferne das Element aus der Liste
                inventar.remove(position);
                // Aktualisiere den Adapter
                adapterShopping.notifyDataSetChanged();
                return true;
            }
        });

    }
}
