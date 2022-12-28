package com.example.ru_kam_recipeapp.service.impl;

import com.example.ru_kam_recipeapp.model.Ingredient;
import com.example.ru_kam_recipeapp.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredientsMap = new HashMap<>();
    private long counter = 0;

    //Метод добавления ингредиента
    @Override
    public Ingredient add(Ingredient ingredient) {
        ingredientsMap.put(this.counter++, ingredient);
        return ingredient;
    }

    //Метод получения ингредиента по идентификатору
    @Override
        public Ingredient get(long id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new RuntimeException("Ингредиент с таким id не найден!");
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
            return ingredient;
        } else {
            throw new RuntimeException("Ингредиент с таким id не найден!");
        }
    }
    //Метод удаления ингредиента
    @Override
    public Ingredient delete(long id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.remove(id);
        } else {
            throw new RuntimeException("Ингредиент с таким id не найден!");
        }
    }
}
