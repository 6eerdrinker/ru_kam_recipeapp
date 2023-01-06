package com.example.ru_kam_recipeapp.service;

public interface IngredientFilesService {


    boolean saveIngredientToJsonFile(String json);

    String readIngredientFromJsonFile();

    void cleanIngredientDataFile();
}
