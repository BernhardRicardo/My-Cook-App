package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        int id = getIntent().getIntExtra("id", 0);


        String title = getIntent().getStringExtra("title");
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        ArrayList<String> instructions = getIntent().getStringArrayListExtra("instructions");
        String image = getIntent().getStringExtra("image");


        TextView tvTitle = findViewById(R.id.recipe_title);
        ImageView ivImage = findViewById(R.id.recipe_image);


        tvTitle.setText(title);
        Glide.with(this).load(image).into(ivImage);

        ListView lvIngredients = findViewById(R.id.ingredients_list_view);
        ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);
        lvIngredients.setAdapter(ingredientsAdapter);

        ListView lvInstructions = findViewById(R.id.instructions_list_view);
        ArrayAdapter<String> instructionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, instructions);
        lvInstructions.setAdapter(instructionsAdapter);


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
