package com.example.ru_kam_recipeapp.service.impl;
import com.example.ru_kam_recipeapp.service.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public boolean saveIngredientToJsonFile(String json) {
        try {
            cleanIngredientDataFile();
            Files.writeString(Path.of(ingredientFileDirectory, nameDataIngredientFile), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readIngredientFromJsonFile() {
        try {
            return Files.readString(Path.of(ingredientFileDirectory, nameDataIngredientFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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




