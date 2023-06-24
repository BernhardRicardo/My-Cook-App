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
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class FavoritesActivity extends AppCompatActivity implements RecyclerViewInterface {

    ContainerRecipes cr = new ContainerRecipes();


    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter favRecyclerViewAdapter;

    int []arr = {R.drawable.carbonara, R.drawable.carbonara, R.drawable.carbonara, R.drawable.carbonara};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ArrayList<String> tmp = new ArrayList<String>();
        ArrayList<String> tmp1 = new ArrayList<String>();


        //RecipeLocal rl = new RecipeLocal(2, "TEST2", tmp, tmp1, "TESTIMAGE2", 2, 2);
        //cr.localRecipeList.add(rl);
       // cr.saveData();
        //cr.loadData();

/*
        recyclerView =  findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Pass in an array of images to display
        favRecyclerViewAdapter = new RecyclerViewAdapter(arr, this);

        recyclerView.setAdapter(favRecyclerViewAdapter);
        recyclerView.setHasFixedSize(true);*/
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
        
    }
}


