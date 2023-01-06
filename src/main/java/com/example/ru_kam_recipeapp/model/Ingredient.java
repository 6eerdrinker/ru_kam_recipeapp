package com.example.ru_kam_recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private  String ingredientName;

    private  int amount;

    private  String unit;


}
