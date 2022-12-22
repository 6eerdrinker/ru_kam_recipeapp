package com.example.ru_kam_recipeapp.service;

import com.example.ru_kam_recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
    private final Map<String, Recipe> recipes = new HashMap<>();


    public Recipe addRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.getId())) {
            throw new RuntimeException("Такой рецепт уже есть в списке!");
        }else {
            recipes.put(recipe.getId(), recipe);
        }
        return recipe;
    }

    public Recipe getRecipe(String id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Номер рецепта не найден!");
        }
    }

    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }
}
