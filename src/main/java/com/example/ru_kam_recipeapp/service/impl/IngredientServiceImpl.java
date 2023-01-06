package com.example.ru_kam_recipeapp.service.impl;

import com.example.ru_kam_recipeapp.model.Ingredient;
import com.example.ru_kam_recipeapp.service.IngredientFilesService;
import com.example.ru_kam_recipeapp.service.IngredientService;
import com.example.ru_kam_recipeapp.service.myException.JsonMyException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service

public class IngredientServiceImpl implements IngredientService {

    private final IngredientFilesService filesService;
    private LinkedHashMap<Long, Ingredient> ingredientsMap = new LinkedHashMap<>();
    private long counter = 0;

    public IngredientServiceImpl(IngredientFilesService filesService) {
        this.filesService = filesService;
    }
@PostConstruct
    private void initIngredient() {
        readIngredientFromJsonFile();
    }

    //Метод добавления ингредиента
    @Override
    public Ingredient add(Ingredient ingredient) {
        ingredientsMap.put(this.counter++, ingredient);
        saveIngredientToJsonFile();
        return ingredient;
    }

    //Метод получения ингредиента по идентификатору
    @Override
        public Ingredient get(long id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new JsonMyException("Ингредиент с таким id не найден!");
        }
    }

    //Метод поучения списка ингредиентов
    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientsMap.values());
    }

    //Метод редактирования ингредиента
    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredient);
            saveIngredientToJsonFile();
            return ingredient;
        } else {
            throw new JsonMyException("Ингредиент с таким id не найден!");
        }
    }
    //Метод удаления ингредиента
    @Override
    public Ingredient delete(long id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.remove(id);
        } else {
            throw new JsonMyException("Ингредиент с таким id не найден!");
        }
    }

    private void saveIngredientToJsonFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesService.saveIngredientToJsonFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JsonMyException e) {
            throw new JsonMyException("Не удалось записать Json-файл!");
        }
    }

    private void readIngredientFromJsonFile() {
        String json = filesService.readIngredientFromJsonFile();
        try {
            ingredientsMap = new ObjectMapper().readValue(json,
                    new TypeReference<LinkedHashMap<Long, Ingredient>>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JsonMyException e) {
            throw new JsonMyException("Не удалось прочитать Json-файл!");
        }
    }
}
