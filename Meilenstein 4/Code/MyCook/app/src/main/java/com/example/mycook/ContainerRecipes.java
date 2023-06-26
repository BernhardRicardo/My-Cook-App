package com.example.mycook;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContainerRecipes {

    private static String JSON_FILE_PATH = "/data/data/com.example.mycook/files/localRecipes.json";
    File f = new File(JSON_FILE_PATH);


    public ArrayList<RecipeLocal> localRecipeList = new ArrayList<>();

    public ContainerRecipes() {

    }

    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(f);
            Gson g = new Gson();
            g.toJson(localRecipeList, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            //Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG).show();
            System.err.println("Writing Error");
        }
    }

    public void loadData() {
        if (f.exists()) {
            try {
                FileReader fileReader = new FileReader(f);
                Type type = new TypeToken<ArrayList<RecipeLocal>>() {
                }.getType();
                Gson gson = new Gson();
                localRecipeList = gson.fromJson(fileReader, type);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            //Toast.makeText(this, "Nothing happened", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteRecipe(int id, String title){
        for(int i = 0; i < localRecipeList.size(); i++){
            if(localRecipeList.get(i).getId() == id && localRecipeList.get(i).getTitle().equals(title)){
                localRecipeList.remove(i);
                break;
            }
        }
    }
}


