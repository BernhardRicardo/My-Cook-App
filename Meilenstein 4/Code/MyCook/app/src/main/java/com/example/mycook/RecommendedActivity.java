package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class RecommendedActivity extends AppCompatActivity implements RecyclerViewInterface{

    BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;
    private EditText editTextSearch;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter recyclerViewAdapter;


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
                    searchRecipe(searchKey);

                    return true;
                }
                return false;
            }
        });

        // Change this with ingredients from inventory later
        searchRecipe("chicken");


        bottomNavigationView = findViewById(R.id.b_favorites);
        bottomNavigationView.setSelectedItemId(R.id.b_favorites);
        //Function deprecated--> maybe switch in future
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.b_inventory) {
                    startActivity(new Intent(getApplicationContext(), InventoryListActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.b_shopping) {
                    startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.b_favorites) {
                    startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else
                    return false;
                }
            }
        );

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }

    private void searchRecipe(String ingredient){
        editTextSearch.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);

        List<Food> arrFood = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients")
                .newBuilder();
        urlBuilder.addQueryParameter("ingredients", ingredient);
        urlBuilder.addQueryParameter("number", "20");
        urlBuilder.addQueryParameter("ignorePantry", "true");
        urlBuilder.addQueryParameter("ranking", "1");

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .addHeader("X-RapidAPI-Key", "76d8c40b12msh5d1ad7c60215853p1e3045jsn8e82d8f51cb0")
                .addHeader("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //textViewResult.setText(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    try {
                        JSONArray json = new JSONArray(myResponse);

                        for (int i = 0; i < json.length(); i++) {
                            JSONObject obj = json.getJSONObject(i);
                            String title = obj.getString("title");
                            String image = obj.getString("image");
                            int id = obj.getInt("id");

                            arrFood.add(new Food(id, title, image));
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    RecommendedActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView = findViewById(R.id.recyclerView);
                            layoutManager = new GridLayoutManager(RecommendedActivity.this, 2);
                            recyclerView.setLayoutManager(layoutManager);

                            // Pass in an array of images to display
                            recyclerViewAdapter = new RecyclerViewAdapter(arrFood, RecommendedActivity.this);

                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerView.setHasFixedSize(true);
                        }
                    });
                }
            }
        });
    }
}