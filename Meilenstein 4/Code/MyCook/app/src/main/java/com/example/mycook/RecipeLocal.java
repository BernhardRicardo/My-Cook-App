package com.example.mycook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecipeLocal {

    public RecipeLocal(int id, String title, ArrayList<String> ingredients, ArrayList<String> instructions, String stringimage, int intimage) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.stringimage = stringimage;
        this.intimage = intimage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public String getStringimage() {
        return stringimage;
    }

    public void setStringimage(String stringimage) {
        this.stringimage = stringimage;
    }

    public int getIntimage() {
        return intimage;
    }

    public void setIntimage(int intimage) {
        this.intimage = intimage;
    }

    private int id;
    private String title;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private String stringimage;
    private int intimage;

    private Bitmap pic;

    public void setEncodedImage(Bitmap b) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        this.stringimage = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public Bitmap getDecodedImage() {
        if(this.intimage == 0){
            return null;
        }
        byte[] decodedString = Base64.decode(this.stringimage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
