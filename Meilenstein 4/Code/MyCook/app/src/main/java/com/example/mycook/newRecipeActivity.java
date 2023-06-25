package com.example.mycook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import androidx.core.content.FileProvider;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.widget.AdapterView;

import com.example.mycook.databinding.ActivityNewRecipeBinding;
import com.google.gson.Gson;



public class newRecipeActivity extends AppCompatActivity {

    ActivityNewRecipeBinding newRecipeBinding;
    ActivityResultLauncher<Uri> takePictureLauncher;
    Uri imageUri;
    Bitmap bitmap;

    String sImage;
    ContainerRecipes cr = new ContainerRecipes();

    private static final int CAMERA_PERMISSION_CODE = 1;

    public ArrayList<String> stepsList = new ArrayList<>();
    public ArrayList<String> ingridientsList = new ArrayList<>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newRecipeBinding = ActivityNewRecipeBinding.inflate(getLayoutInflater());
        setContentView(newRecipeBinding.getRoot());

        imageUri = createUri();
        registerPictureLauncher();

        newRecipeBinding.recipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
            }
        });



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
                stepEt.setText("");
            }
        });

        addIngriedientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingridientsList.add(ingredientsEt.getText().toString());
                ingredientsAdapter.notifyDataSetChanged();
                ingredientsEt.setText("");
            }
        });

        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeLocal recipe = new RecipeLocal(0, recipeTitelEt.getText().toString(), ingridientsList, stepsList, sImage, 1);
                cr.localRecipeList.add(recipe);
                recipe.setEncodedImage(bitmap);
                cr.saveData();
                finish();
            }
        });
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    private Uri createUri() {
        File imageFile = new File(getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(getApplicationContext(), "com.example.mycook.fileProvider", imageFile);
    }

    private void registerPictureLauncher() {
        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                try {
                    if (result) {
                        newRecipeBinding.recipeImage.setImageURI(null);
                        try {

                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            newRecipeBinding.recipeImage.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        newRecipeBinding.recipeImage.setImageURI(imageUri);
                        System.out.println("Image saved" + sImage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkCameraPermission() {
        if (ActivityCompat.checkSelfPermission(newRecipeActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(newRecipeActivity.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }else{
            takePictureLauncher.launch(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictureLauncher.launch(imageUri);
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}