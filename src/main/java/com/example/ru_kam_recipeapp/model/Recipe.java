package com.example.ru_kam_recipeapp.model;

import java.util.ArrayList;
import java.util.List;


public class Recipe {
    private final String id;
    private final String recipeName;
    private final int preparingTime;
    private final ArrayList<Ingredient> ingredients = new ArrayList<>(50);
    private final ArrayList<String> cookingStep = new ArrayList<>();

    public Recipe(String id, String recipeName, int preparingTime) {
        this.id = id;
        this.recipeName = recipeName;
        this.preparingTime = preparingTime;
    }

    public String getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getCookingStep() {
        return cookingStep;
    }

}
