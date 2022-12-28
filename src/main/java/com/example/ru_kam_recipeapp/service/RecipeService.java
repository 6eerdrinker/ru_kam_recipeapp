package com.example.ru_kam_recipeapp.service;

import com.example.ru_kam_recipeapp.model.Recipe;

import java.util.List;


public interface RecipeService {


    //Метод добавления рецепта
    Recipe add(Recipe recipe);

    //Метод получения рецепта по идентификатору
    Recipe get(long id);

    //Метод получения списка рецептов
    List<Recipe> getAll();

    //Метод обновления рецепта по идентификатору
    Recipe update(long id, Recipe recipe);

    //Метод удаления рецепта по идентификатору
    Recipe delete(long id);
}
