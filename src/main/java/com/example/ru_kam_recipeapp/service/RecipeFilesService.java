package com.example.ru_kam_recipeapp.service;

public interface RecipeFilesService {
    void saveRecipeToJsonFile(String json);

    String readRecipeFromJsonFile();


    void cleanRecipeDataFile();

}

