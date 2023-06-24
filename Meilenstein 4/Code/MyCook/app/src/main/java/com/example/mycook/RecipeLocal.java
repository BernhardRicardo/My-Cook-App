package com.example.mycook;

import java.util.ArrayList;

public class RecipeLocal {

    public RecipeLocal(int id, String title, ArrayList<String> ingredients, ArrayList<String> instructions, String stringimage, int intimage, int size) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.stringimage = stringimage;
        this.intimage = intimage;
        this.favoriteArraylenght = size;
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
    private int favoriteArraylenght;
}
