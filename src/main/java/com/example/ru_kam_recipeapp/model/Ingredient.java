package com.example.ru_kam_recipeapp.model;

import java.util.List;

public class Ingredient {

    private  String ingredientName;
    private  int amount;
    private  String unit;

    private List<Ingredient> ingredients;



    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
