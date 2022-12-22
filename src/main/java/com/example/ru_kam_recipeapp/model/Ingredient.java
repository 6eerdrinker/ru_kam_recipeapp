package com.example.ru_kam_recipeapp.model;

public class Ingredient {

    private final String ingredientName;
    private final int amount;
    private final String unit;

    public Ingredient(String ingredientName, int amount, String unit) {

        this.ingredientName = ingredientName;
        this.amount = amount;
        this.unit = unit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
}
