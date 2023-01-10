package com.example.ru_kam_recipeapp.service.impl;
import com.example.ru_kam_recipeapp.service.RecipeFilesService;
import com.example.ru_kam_recipeapp.service.myException.JsonMyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFilesServiceImpl implements RecipeFilesService {

    @Value("${path.to.recipe.file}")
    private String recipeFileDirectory;


    @Value("${name.of.recipe.file}")
    private String nameDataRecipeFile;


    @Override
    public void saveRecipeToJsonFile(String json) {
        try {
            cleanRecipeDataFile();
            Files.writeString(Path.of(recipeFileDirectory, nameDataRecipeFile), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String readRecipeFromJsonFile() {
        try {
            return Files.readString(Path.of(recipeFileDirectory, nameDataRecipeFile));
        } catch (IOException e) {
            throw new JsonMyException("Не удалось прочитать Json-файл!");
        }
    }

    @Override
    public File getRecipeDataFile() {
        return new File(recipeFileDirectory +"/"+ nameDataRecipeFile);
    }
    @Override
    public void cleanRecipeDataFile() {
        try {
            Path path = Path.of(recipeFileDirectory, nameDataRecipeFile);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

