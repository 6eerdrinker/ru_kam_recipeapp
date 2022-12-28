package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.model.Recipe;
import com.example.ru_kam_recipeapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe addRecipe (@RequestBody Recipe recipe) {
        return recipeService.add(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") long id) {
        return recipeService.get(id);
    }

    @GetMapping
    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.delete(id);
    }
}


