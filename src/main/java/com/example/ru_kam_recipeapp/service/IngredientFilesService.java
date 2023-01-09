package com.example.ru_kam_recipeapp.service;

import java.io.File;
import java.nio.file.Path;

public interface IngredientFilesService {


    void saveIngredientToJsonFile(String json);

    String readIngredientFromJsonFile();

    File getIngredientDataFile();

    void cleanIngredientDataFile();
}
