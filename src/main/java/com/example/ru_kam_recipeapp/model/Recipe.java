package com.example.ru_kam_recipeapp.model;

import java.util.ArrayList;
import java.util.List;


public class Recipe {
    private String recipeName;

    private int preparingTime;

    private List<Ingredient> ingredients;

    private List<String> cookingSteps;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(int preparingTime) {
        this.preparingTime = preparingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(List<String> cookingStep) {
        this.cookingSteps = cookingStep;
    }
}
