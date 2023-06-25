/*TO-DO
-add 3 screens -> recipe, recommended, search
-design all screens and add correct functions
*/

package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class FavoritesActivity extends AppCompatActivity implements RecyclerViewInterface {

    ContainerRecipes cr = new ContainerRecipes();

    private int imageResource;
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter favRecyclerViewAdapter;
    Button AddRecipeButton;

    public List<Food> arrFood = new ArrayList<>();

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
        setContentView(R.layout.activity_favorites);


        cr.loadData();
        String uri = "@drawable/mycooksqr";

        for(int i = 0; i < cr.localRecipeList.size(); i++){
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            int id = cr.localRecipeList.get(i).getId();
            String title = cr.localRecipeList.get(i).getTitle();
            String strImage = cr.localRecipeList.get(i).getStringimage();
            int intImage = cr.localRecipeList.get(i).getIntimage();
            if(intImage == 0){
                if (strImage == null){
                    arrFood.add(new Food(id, title, imageResource));
                }else {
                    arrFood.add(new Food(id, title, strImage));
                }
            }else{
                arrFood.add(new Food(id, title, intImage));
            }
        }


        recyclerView =  findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Pass in an array of images to display
        favRecyclerViewAdapter = new RecyclerViewAdapter(arrFood, this);

        recyclerView.setAdapter(favRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);


        recyclerView.setHasFixedSize(true);
        //Menu-Bar
        bottomNavigationView = findViewById(R.id.b_favorites);
        bottomNavigationView.setSelectedItemId(R.id.b_favorites);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.b_inventory) {
                    startActivity(new Intent(getApplicationContext(), InventoryListActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if (item.getItemId() == R.id.b_shopping){
                    startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else
                    return false;
            }
        }
        );
    }
    public void registerImageClick(View view){
        startActivity(new Intent(getApplicationContext(), RecipeActivity.class));
    }

    public void recommendedClick(View view){
        bottomNavigationView.setSelectedItemId(0);
        startActivity(new Intent(getApplicationContext(), RecommendedActivity.class));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("id", cr.localRecipeList.get(position).getId());
        intent.putExtra("title", cr.localRecipeList.get(position).getTitle());
        intent.putExtra("ingredients", cr.localRecipeList.get(position).getIngredients());
        intent.putExtra("instructions", cr.localRecipeList.get(position).getInstructions());
        intent.putExtra("image", cr.localRecipeList.get(position).getStringimage());
        intent.putExtra("intImage", cr.localRecipeList.get(position).getIntimage());
        intent.putExtra("isFavorite", true);
        intent.putExtra("activity", "FavoritesActivity");
        startActivity(intent);
    }

    public void refreshRecyclerView(){
        cr.loadData();
        for(int i = 0; i < cr.localRecipeList.size(); i++){
            int id = cr.localRecipeList.get(i).getId();
            String title = cr.localRecipeList.get(i).getTitle();
            String strImage = cr.localRecipeList.get(i).getStringimage();
            int intImage = cr.localRecipeList.get(i).getIntimage();
            if(intImage == 0){
                arrFood.add(new Food(id, title, strImage));
            }else{
                arrFood.add(new Food(id, title, intImage));
            }
        }
        favRecyclerViewAdapter.notifyDataSetChanged();
    }




    public void addRecipteButton(View view) {
        startActivity(new Intent(getApplicationContext(), newRecipeActivity.class));
    }
}


