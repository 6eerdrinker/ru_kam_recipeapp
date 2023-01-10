package com.example.ru_kam_recipeapp.service;

import java.io.File;

public interface RecipeFilesService {
    void saveRecipeToJsonFile(String json);

    String readRecipeFromJsonFile();


    void cleanRecipeDataFile();

    File getRecipeDataFile();
}

