package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.model.Recipe;
import com.example.ru_kam_recipeapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
@GetMapping
    public Collection<Recipe> getAllRecipes() {
    return this.recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable ("id") String id) {
        return this.recipeService.getRecipe(id);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }
}
