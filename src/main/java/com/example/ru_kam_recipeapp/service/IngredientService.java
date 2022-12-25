package com.example.ru_kam_recipeapp.service;

import com.example.ru_kam_recipeapp.model.Ingredient;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class IngredientService {

    private final Map<String, Ingredient> ingredients = new HashMap<>();


    public Ingredient getIngredient(String id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Такой номер ингредиента не найден!");
        }
    }
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getIngredientName())) {
            throw new RuntimeException("Такой ингредиент уже есть!");
        }else {
            ingredients.put(ingredient.getIngredientName(), ingredient);
        }
        return ingredient;
    }

}
