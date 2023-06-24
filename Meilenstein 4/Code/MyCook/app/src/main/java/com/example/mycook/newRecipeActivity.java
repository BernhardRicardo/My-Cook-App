package com.example.mycook;

import android.os.Bundle;


import com.google.android.material.snackbar.Snackbar;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.widget.AdapterView;

import com.example.mycook.databinding.ActivityNewRecipeBinding;
import com.google.gson.Gson;



public class newRecipeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewRecipeBinding binding;
    public ArrayList<String> stepsList = new ArrayList<String>();
    public ArrayList<String> ingridientsList = new ArrayList<String>();
    public String recipeTitel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);



        ListView stepsListView = findViewById(R.id.instructions_list_view);

        ArrayAdapter<String> stepsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,stepsList);
        stepsListView.setAdapter(stepsAdapter);



        ListView ingredientsListView = findViewById(R.id.ingredients_list_view);
        ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,ingridientsList);
        ingredientsListView.setAdapter(ingredientsAdapter);

        Button addStepButton = findViewById(R.id.buttonAddSteps);

        Button addIngriedientButton = findViewById(R.id.buttonAddingrediets);

        Button addToFavoriteButton = findViewById(R.id.newRecipeDone);

        EditText recipeTitelEt = findViewById(R.id.recipe_title);

        EditText ingredientsEt = findViewById(R.id.ingredientsEdittext);

        EditText stepEt = findViewById(R.id.stepsEdittext);

        addStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsList.add(stepEt.getText().toString());
                stepsAdapter.notifyDataSetChanged();
            }
        });

        addIngriedientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingridientsList.add(ingredientsEt.getText().toString());
                ingredientsAdapter.notifyDataSetChanged();
            }
        });

        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeTitel = recipeTitelEt.getText().toString();
                String tmpStringImg = "";
                idCnt= idCnt +1; //fürDieNamensgebungderJsonFile
                //int id, String title, ArrayList<String> ingredients, ArrayList<String> instructions, String stringimage, int intimage, int size
                RecipeLocal recipe = new RecipeLocal(0, recipeTitel, ingridientsList, stepsList, tmpStringImg, 0, 0);
                speichern(recipe);
            }
        });

       /* public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
            File dir = new File(mcoContext.getFilesDir(), "mydir");
            if(!dir.exists()){
                dir.mkdir();
            }

            try {
                File gpxfile = new File(dir, sFileName);
                FileWriter writer = new FileWriter(gpxfile);
                writer.append(sBody);
                writer.flush();
                writer.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        } */



    }

    public void speichern(RecipeLocal r)
    {

    }

    public void addToStepsList(View view){

    }



    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);

    }

    private int idCnt= 0;

}