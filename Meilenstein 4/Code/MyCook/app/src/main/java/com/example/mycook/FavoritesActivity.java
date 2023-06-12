/*TO-DO
-add 3 screens -> recipe, recommended, search
-design all screens and add correct functions
*/

package com.example.mycook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;

public class FavoritesActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
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

}


