package com.example.mycook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Food {

    public Food(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.intImage = 0;
    }

    public Food(int id, String title, int image) {
        this.id = id;
        this.title = title;
        this.intImage = image;
        this.image = "";
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

    public String getImage() {
        return image;
    }

    public void setEncodedImage(Bitmap b) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        this.image = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public Bitmap getDecodedImage() {
        byte[] decodedString = Base64.decode(this.image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public int getIntImage() {
        return intImage;
    }

    public void setIntImage(int intImage) {
        this.intImage = intImage;
    }

    private int id;
    private String title;
    private String image;

    private int intImage;
}
