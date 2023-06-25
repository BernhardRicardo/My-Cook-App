package com.example.mycook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import com.google.android.material.snackbar.Snackbar;


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


    /*String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                int REQUEST_IMAGE_CAPTURE = new Integer(0);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    } */

    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    private ImageView imageView;

    private int imageResource;
    private Bitmap imageBitmap;

    private String image;

    private String sharedFact;

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewRecipeBinding binding;
    public ArrayList<String> stepsList = new ArrayList<String>();
    public ArrayList<String> ingridientsList = new ArrayList<String>();
    public String recipeTitel;

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
                sharedFact = imageView.toString();
                imageResource = getResources().getIdentifier(imageBitmap.toString(), null, getPackageName()); //Bitmap to String, klappt nicht...
               // image = bitmapToString(imageBitmap); // Bild zu String (klappt nicht, in Fav wird kein Bild angezeigt)
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

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

        ImageView picture = findViewById(R.id.recipe_image);

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
                ContainerRecipes cr = new ContainerRecipes();
                cr.loadData();
                recipeTitel = recipeTitelEt.getText().toString();
                String tmpStringImg = "";
                idCnt= cr.localRecipeList.size() +1; //fürDieNamensgebungderJsonFile
                //int id, String title, ArrayList<String> ingredients, ArrayList<String> instructions, String stringimage, int intimage
                RecipeLocal recipe = new RecipeLocal(idCnt, recipeTitel, ingridientsList, stepsList, sharedFact, imageResource);


                cr.localRecipeList.add(recipe);
                cr.saveData();
                startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));

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

        imageView = findViewById(R.id.recipe_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(newRecipeActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    ActivityCompat.requestPermissions(newRecipeActivity.this,
                            new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            }
        });

    }

    public String bitmapToString(@NonNull Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }


    public void addToStepsList(View view){

    }



    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);

    }

    private int idCnt= 0;

}