package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.view.View.OnClickListener;
import android.widget.Toast;

public class ShoppingListActivity extends AppCompatActivity {

    private static String SHOPPING_LIST_FILE_PATH = "/data/data/com.example.mycook/files/shoppingList.txt";
    private static String SHOPPING_LIST_FILE = "shoppingList.txt";
    private static String FILE_NAME = "testInventory.txt";
    EditText eingabeText;
    Button addInventoryButton;
    Button addButton;
    ListView shoppingListView;
    ArrayAdapter<String> adapterShopping;
    ArrayList<String> shoppingItems = new ArrayList<String>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        loadShoppingList();
        //Menu-Bar
        bottomNavigationView = findViewById(R.id.b_shopping);
        bottomNavigationView.setSelectedItemId(R.id.b_shopping);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.b_inventory) {
                    saveShoppingListData();
                    startActivity(new Intent(getApplicationContext(), InventoryListActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.b_favorites) {
                    saveShoppingListData();
                    startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else
                    return false;
            }
        }
        );

        eingabeText = findViewById(R.id.eingabefeldShoppinglist);
        addButton = findViewById(R.id.buttonAddShoppinglist);
        shoppingListView = findViewById(R.id.listviewShoppinglist);
        addInventoryButton = findViewById(R.id.addItems);
        adapterShopping = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, shoppingItems);
        shoppingListView.setAdapter(adapterShopping);

       // Intent intent = new Intent(this, InventoryListActivity.class);
        //intent.putStringArrayListExtra("inventar", shoppingItems);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Füge das eingegebene Element zur Liste hinzu
                String newItem = eingabeText.getText().toString();
                shoppingItems.add(newItem);
                // Aktualisiere den Adapter
                adapterShopping.notifyDataSetChanged();
                // Setze das Eingabefeld zurück
                eingabeText.setText("");


            }
        });

        shoppingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Entferne das Element aus der Liste
                shoppingItems.remove(position);
                // Aktualisiere den Adapter
                adapterShopping.notifyDataSetChanged();
                return true;
            }
        });
        addInventoryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItem = "";
                List<Integer> checkedIndices = new ArrayList<>(); // Store checked item indices

                // Iterate over items in reverse order
                for (int i = shoppingListView.getCount() - 1; i >= 0; i--) {
                    if (shoppingListView.isItemChecked(i)) {
                        shoppingListView.setItemChecked(i,false);
                        selectedItem += shoppingListView.getItemAtPosition(i) + "\n";
                        checkedIndices.add(i);
                    }
                }

                // Remove items from shoppingItems list
                for (int index : checkedIndices) {
                    shoppingItems.remove(index);
                }

                // Notify the adapter to update the view
                adapterShopping.notifyDataSetChanged();

                // Rest of the code to write the selected items to the inventory file
                FileOutputStream fos = null;
                String text = selectedItem.toString();

                try {
                    fos = openFileOutput(FILE_NAME, MODE_APPEND);
                    fos.write(text.getBytes());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
    public void loadShoppingList() {
        File f = new File(SHOPPING_LIST_FILE_PATH);
        if (f.exists() == true) {
            //Opens file if existing
            FileInputStream fis = null;
            try {
                fis = openFileInput(SHOPPING_LIST_FILE);
                InputStreamReader isr = new InputStreamReader(fis);

                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text;

                while ((text = br.readLine()) != null) {
                    shoppingItems.add(text);
                }


            } catch (FileNotFoundException e) {
                throw new RuntimeException("FILE NOT FOUND");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            shoppingListView = findViewById(R.id.listviewShoppinglist);
            adapterShopping = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, shoppingItems);
            shoppingListView.setAdapter(adapterShopping);
        }
        else {
            //No Inventory file
            //Toast.makeText(this, "Nothing in your inventory!", Toast.LENGTH_LONG).show();
            shoppingListView = findViewById(R.id.listviewShoppinglist);
            adapterShopping = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, shoppingItems);
            shoppingListView.setAdapter(adapterShopping);
        }
    }

    public void saveShoppingListData(){
        String selectedItem = "";
        for (int i = 0; i < shoppingListView.getCount(); i++) {
            selectedItem += shoppingListView.getItemAtPosition(i) + "\n";

        }
        //writes into inventory file
        FileOutputStream fos = null;
        String text = selectedItem.toString();

        try {
            fos = openFileOutput(SHOPPING_LIST_FILE, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    //close file
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}


