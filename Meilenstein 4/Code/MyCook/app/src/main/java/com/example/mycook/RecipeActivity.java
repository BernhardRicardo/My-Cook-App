package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ContainerRecipes cr = new ContainerRecipes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        String backActivity = getIntent().getStringExtra("activity");
        boolean isFavorite = getIntent().getBooleanExtra("isFavorite", false);
        int id = getIntent().getIntExtra("id", 0);


        AppCompatButton btnFav = (AppCompatButton)findViewById(R.id.recipe_favourite_button);

        if(isFavorite) {
            btnFav.setBackgroundResource(R.drawable.ic_favorite_star_gold);
        } else {
            btnFav.setBackgroundResource(R.drawable.ic_favorite_star);
        }
        String title = getIntent().getStringExtra("title");
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        ArrayList<String> instructions = getIntent().getStringArrayListExtra("instructions");
        String image = getIntent().getStringExtra("image");
        int intImage = getIntent().getIntExtra("intImage", 0);


        TextView tvTitle = findViewById(R.id.recipe_title);
        ImageView ivImage = findViewById(R.id.recipe_image);


        tvTitle.setText(title);
        if(intImage != 0) {
            ivImage.setImageResource(intImage);
        } else {
            Glide.with(this).load(image).into(ivImage);
        }

        ListView lvIngredients = findViewById(R.id.ingredients_list_view);
        ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);
        lvIngredients.setAdapter(ingredientsAdapter);

        ListView lvInstructions = findViewById(R.id.instructions_list_view);
        ArrayAdapter<String> instructionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, instructions);
        lvInstructions.setAdapter(instructionsAdapter);

        btnFav.setOnClickListener(v -> {
            if(isFavorite) {
                btnFav.setBackgroundResource(R.drawable.ic_favorite_star);
                cr.deleteRecipe(id);
                cr.saveData();
                cr.loadData();

            } else {
                btnFav.setBackgroundResource(R.drawable.ic_favorite_star_gold);
                cr.localRecipeList.add(new RecipeLocal(id, title, ingredients, instructions, image, intImage));
                cr.saveData();
                cr.loadData();
            }
        });

    }
}
