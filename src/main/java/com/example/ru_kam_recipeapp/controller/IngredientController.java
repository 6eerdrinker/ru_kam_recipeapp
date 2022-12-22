package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.model.Ingredient;
import com.example.ru_kam_recipeapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")

public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") String id) {
        return this.ingredientService.getIngredient(id);
    }
    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }
}
