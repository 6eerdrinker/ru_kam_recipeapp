package com.example.ru_kam_recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String recipeName;

    private int preparingTime;

    private List<Ingredient> ingredients;

    private List<String> cookingSteps;

}
