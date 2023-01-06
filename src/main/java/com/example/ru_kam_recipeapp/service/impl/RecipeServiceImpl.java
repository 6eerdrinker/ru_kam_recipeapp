package com.example.ru_kam_recipeapp.service.impl;

import com.example.ru_kam_recipeapp.model.Ingredient;
import com.example.ru_kam_recipeapp.model.Recipe;
import com.example.ru_kam_recipeapp.service.IngredientFilesService;
import com.example.ru_kam_recipeapp.service.RecipeFilesService;
import com.example.ru_kam_recipeapp.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
    public class RecipeServiceImpl implements RecipeService {

        private final RecipeFilesService filesService;
    private LinkedHashMap<Long, Recipe> recipesMap = new LinkedHashMap<>();
    private long counter = 0;

    public RecipeServiceImpl(RecipeFilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void initRecipe() {
    readRecipeFromJsonFile();
    }

    //Метод добавления рецепта
    @Override
    public Recipe add(Recipe recipe) {
        recipesMap.put(this.counter++, recipe);
        saveRecipeToJsonFile();
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
                saveRecipeToJsonFile();
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
    private void saveRecipeToJsonFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipesMap);
            filesService.saveRecipeToJsonFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readRecipeFromJsonFile() {
        String json = filesService.readRecipeFromJsonFile();
        try {
            recipesMap = new ObjectMapper().readValue(json,
                    new TypeReference<LinkedHashMap<Long, Recipe>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    }


