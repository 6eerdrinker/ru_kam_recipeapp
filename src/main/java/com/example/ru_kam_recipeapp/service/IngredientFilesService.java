package com.example.ru_kam_recipeapp.service;

public interface IngredientFilesService {


    void saveIngredientToJsonFile(String json);

    String readIngredientFromJsonFile();

    void cleanIngredientDataFile();
}
