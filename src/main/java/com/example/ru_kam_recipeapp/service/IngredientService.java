package com.example.ru_kam_recipeapp.service;


import com.example.ru_kam_recipeapp.model.Ingredient;

import java.util.List;

public interface IngredientService {
    //Метод добавления ингредиента
    Ingredient add(Ingredient ingredient);

    //Метод получения ингредиента по идентификатору
    Ingredient get(long id);

    //Метод получения списка ингредиентов
    List<Ingredient> getAll();

    //Метод редактирования ингредиента по идентификатору
    Ingredient update(long id, Ingredient ingredient);

    //Метод удаления ингредиента по идентификатору
    Ingredient delete(long id);
}
