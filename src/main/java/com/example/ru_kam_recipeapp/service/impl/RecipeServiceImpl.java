package com.example.ru_kam_recipeapp.service.impl;

import com.example.ru_kam_recipeapp.model.Recipe;
import com.example.ru_kam_recipeapp.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @Service
    public class RecipeServiceImpl implements RecipeService {
    private final Map<Long, Recipe> recipesMap = new HashMap<>();
    private long counter = 0;

    //Метод добавления рецепта
    @Override
    public Recipe add(Recipe recipe) {
        recipesMap.put(this.counter++, recipe);
        return recipe;
    }

    //Метод получения рецепта по идентификатору
    @Override
    public Recipe get(long id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new RuntimeException("Рецепт с таким id не найден!");
        }
    }

        //Метод получения списка рецептов
        @Override
        public List<Recipe> getAll() {
            return new ArrayList<>(this.recipesMap.values());
        }

    //Метод редактирования рецепта
        @Override
        public Recipe update(long id, Recipe recipe) {
            if (recipesMap.containsKey(id)) {
                recipesMap.put(id, recipe);
                return recipe;
            }
            else {
                throw new RuntimeException("Рецепт с таким id не найден!");
            }
        }
    //Метод удаления рецепта
        @Override
        public Recipe delete(long id) {
            if (recipesMap.containsKey(id)) {
                return recipesMap.remove(id);
            } else {
                throw new RuntimeException("Рецепт с таким id не найден!");
            }
        }
    }


