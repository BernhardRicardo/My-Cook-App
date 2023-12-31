package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class InventoryListActivity extends AppCompatActivity {
   //Inventory File
   private static String FILE_PATH = "/data/data/com.example.mycook/files/testInventory.txt";
    private static String FILE_NAME = "testInventory.txt";
    ListView invList;
    ArrayAdapter<String> invAdapter;
    public ArrayList<String> inventarArrayList = new ArrayList<>();
    EditText eingabefeldIneventory;
    Button addItems;


    BottomNavigationView bottomNavigationView;

    @Override
    public void onRestart() {
        super.onRestart();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

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
                    updateInventoryData();
                    startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if (item.getItemId() == R.id.b_favorites){
                    updateInventoryData();
                    startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else
                    return false;
            }
        }
        );
        eingabefeldIneventory = findViewById(R.id.eingabefeldIneventory);
        addItems = findViewById(R.id.addItems);

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = eingabefeldIneventory.getText().toString().trim();
                if (!newItem.isEmpty()) {
                    inventarArrayList.add(newItem);
                    invAdapter.notifyDataSetChanged();
                    eingabefeldIneventory.setText("");
                }
            }
        });


        //Checks if File exits
        File f = new File(FILE_PATH);
        if (f.exists() == true) {
            //Opens file if existing
            FileInputStream fis = null;
            try {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);

                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text;

                while ((text = br.readLine()) != null) {
                    inventarArrayList.add(text);
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
            invList = findViewById(R.id.listInventar);
            invAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inventarArrayList);
            invList.setAdapter(invAdapter);
        }
        else {
            //No Inventory file
            Toast.makeText(this, "Nothing in your inventory!", Toast.LENGTH_LONG).show();
            invList = findViewById(R.id.listInventar);
            invAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inventarArrayList);
            invList.setAdapter(invAdapter);
        }
       invList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Entferne das Element aus der Liste
                inventarArrayList.remove(position); // gehört zu ArrayList
                //Aktualisiere Adapter
                invAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }




    //Updates Inventory file if stuff was deleted, after swapping activity
    public void updateInventoryData(){
        String selectedItem = "";
        for (int i = 0; i < invList.getCount(); i++) {
                selectedItem += invList.getItemAtPosition(i) + "\n";

        }
        //writes into inventory file
        FileOutputStream fos = null;
        String text = selectedItem.toString();

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
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