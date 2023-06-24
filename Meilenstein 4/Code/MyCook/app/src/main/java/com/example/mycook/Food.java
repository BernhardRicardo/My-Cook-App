package com.example.mycook;

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

    public void setImage(String image) {
        this.image = image;
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
