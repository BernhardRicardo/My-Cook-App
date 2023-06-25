package com.example.mycook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    public int getIntImage() {
        return intImage;
    }

    public void setIntImage(int intImage) {
        this.intImage = intImage;
    }

    int intImage;
    BottomNavigationView bottomNavigationView;
    ContainerRecipes cr = new ContainerRecipes();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cr.loadData();
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
        intImage= getIntent().getIntExtra("intImage", 0);
        String uri = "@drawable/mycooksqr";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        TextView tvTitle = findViewById(R.id.recipe_title);
        ImageView ivImage = findViewById(R.id.recipe_image);


        tvTitle.setText(title);
        if(intImage != 0) {
            ivImage.setImageResource(intImage);

        } else {
            //setIntImage(noImage);
            if (image != null) {
                Glide.with(this).load(image).into(ivImage);
            }
            else {
                ivImage.setImageResource(imageResource);
                int desiredWidthInDp = 400; // Die gewünschte Breite in dp
                int desiredHeightInDp = 400; // Die gewünschte Höhe in dp
                // Konvertiere die Breite und Höhe von dp in Pixel
                float density = ivImage.getContext().getResources().getDisplayMetrics().density;
                int desiredWidthInPx = (int) (desiredWidthInDp * density);
                int desiredHeightInPx = (int) (desiredHeightInDp * density);

                ViewGroup.LayoutParams layoutParams = ivImage.getLayoutParams();
                layoutParams.width = desiredWidthInPx;
                layoutParams.height = desiredHeightInPx;
                ivImage.setLayoutParams(layoutParams);

                // Platziere das ImageView mit Abstand zum oberen Rand und zentriere es horizontal
                RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(desiredWidthInPx, desiredHeightInPx);
                imageParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                imageParams.topMargin = 100; // Abstand zum oberen Rand in Pixel, hier 32 Pixel als Beispiel
                ivImage.setLayoutParams(imageParams);
            }
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
            } else {

                btnFav.setBackgroundResource(R.drawable.ic_favorite_star_gold);
                cr.localRecipeList.add(new RecipeLocal(id, title, ingredients, instructions, image, intImage));
                cr.saveData();
                for(int i = 0; i < cr.localRecipeList.size(); i++){
                    System.out.println(cr.localRecipeList.size()+"AAAAAAAAAAAAAAAAA");
                    System.out.println(cr.localRecipeList.get(i).getTitle());
                }
            }
        });

    }
}
