package com.example.ru_kam_recipeapp.service.impl;
import com.example.ru_kam_recipeapp.service.IngredientFilesService;
import com.example.ru_kam_recipeapp.service.myException.JsonMyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class IngredientFilesServiceImpl implements IngredientFilesService {

    @Value("${path.to.ingredient.file}")
    private String ingredientFileDirectory;

    @Value("${name.of.ingredient.file}")
    private String nameDataIngredientFile;

    @Override
    public void saveIngredientToJsonFile(String json) {
        try {
            cleanIngredientDataFile();
            Files.writeString(Path.of(ingredientFileDirectory, nameDataIngredientFile), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readIngredientFromJsonFile() {
        try {
            return Files.readString(Path.of(ingredientFileDirectory, nameDataIngredientFile));
        } catch (IOException e) {
            throw new JsonMyException("Не удалось прочитать Json-файл!");
        }
    }

    @Override
    public File getIngredientDataFile() {
        return new File(ingredientFileDirectory + "/" + nameDataIngredientFile);
    }

@Override
    public void cleanIngredientDataFile() {
        try {
            Path path = Path.of(ingredientFileDirectory, nameDataIngredientFile);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




