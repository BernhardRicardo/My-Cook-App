package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RecommendedActivity extends AppCompatActivity implements RecyclerViewInterface{

    BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;
    private EditText editTextSearch;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    int []arr = {R.drawable.carbonara, R.drawable.carbonara, R.drawable.carbonara, R.drawable.carbonara};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        editTextSearch = findViewById(R.id.searchRecipe);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String searchKey = v.getText().toString();

                    return true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Pass in an array of images to display
        recyclerViewAdapter = new RecyclerViewAdapter(arr, this);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);

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
        );
        */
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }
}