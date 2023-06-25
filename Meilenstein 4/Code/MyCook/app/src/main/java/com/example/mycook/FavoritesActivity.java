/*TO-DO
-add 3 screens -> recipe, recommended, search
-design all screens and add correct functions
*/

package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Button;
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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FavoritesActivity extends AppCompatActivity implements RecyclerViewInterface {

    ContainerRecipes cr = new ContainerRecipes();
    EditText etSearch;
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
        String uri = "@drawable/mycooksqr";

        cr.loadData();

        for(int i = 0; i < cr.localRecipeList.size(); i++){
            int id = cr.localRecipeList.get(i).getId();
            String title = cr.localRecipeList.get(i).getTitle();
            String strImage = cr.localRecipeList.get(i).getStringimage();
            int intImage = cr.localRecipeList.get(i).getIntimage();
            imageResource = getResources().getIdentifier(uri, null, getPackageName());
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

        etSearch = findViewById(R.id.searchRecipe);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String searchKey = v.getText().toString();
                    searchRecipe(searchKey);

                    return true;
                }
                return false;
            }
        });

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

    private void searchRecipeInfo(int id, Intent intent){



                    try {
                        JSONObject json = new JSONObject();
                        String title = json.getString("title");
                        String image = json.getString("image");
                        //int id = json.getInt("id");

                        ArrayList<String> ingredients = new ArrayList<>();
                        JSONArray jsonIngredients = json.getJSONArray("extendedIngredients");
                        for(int i = 0; i < jsonIngredients.length(); i++){
                            JSONObject jsonIngredient = jsonIngredients.getJSONObject(i);
                            int amount = jsonIngredient.getInt("amount");
                            String strAmount = Integer.toString(amount);
                            String ingredient = strAmount + " " + jsonIngredient.getString("unit") + " " + jsonIngredient.getString("name");
                            ingredients.add(ingredient);
                        }

                        ArrayList<String> instructions = new ArrayList<>();
                        JSONArray jsonInstructions = json.getJSONArray("analyzedInstructions");
                        for(int i = 0; i < jsonInstructions.length(); i++){
                            JSONObject jsonInstruction = jsonInstructions.getJSONObject(i);
                            JSONArray jsonSteps = jsonInstruction.getJSONArray("steps");
                            for(int j = 0; j < jsonSteps.length(); j++){
                                JSONObject jsonStep = jsonSteps.getJSONObject(j);
                                String step = jsonStep.getString("step");
                                instructions.add(step);
                            }
                        }

                        FavoritesActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra("title", title);
                                intent.putExtra("image", image);
                                intent.putExtra("intImage", 0);
                                intent.putExtra("id", id);
                                intent.putExtra("ingredients", ingredients);
                                intent.putExtra("instructions", instructions);
                                intent.putExtra("isFavorite", false);
                                intent.putExtra("activity", "RecommendedActivity");
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();




        }
    }
    private void searchRecipe(String ingredient){
        etSearch.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);

        arrFood.clear();
            for (RecipeLocal recipe : cr.localRecipeList){
                if (recipe.getTitle().contains(ingredient) || recipe.getIngredients().contains(ingredient)){
                    int id = recipe.getId();
                    String title = recipe.getTitle();
                    String strImage = recipe.getStringimage();
                    int intImage = recipe.getIntimage();
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
            }


                    FavoritesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView = findViewById(R.id.recyclerView);
                            layoutManager = new GridLayoutManager(FavoritesActivity.this, 2);
                            recyclerView.setLayoutManager(layoutManager);

                            // Pass in an array of images to display
                            favRecyclerViewAdapter = new RecyclerViewAdapter(arrFood, FavoritesActivity.this);

                            recyclerView.setAdapter(favRecyclerViewAdapter);
                            recyclerView.setHasFixedSize(true);
                        }
                    });


        ;
    }
}

