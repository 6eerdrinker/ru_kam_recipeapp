package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.model.Recipe;
import com.example.ru_kam_recipeapp.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")
@Tag(name = "КУЛИНАРНЫЕ РЕЦЕПТЫ", description = "Возможность добавления нового рецепта, " +
        "просмотра списка рецептов, просмотр, редактирование и удаление рецепта по идентификатору")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(
            summary = "ДОБАВЛЕНИЕ НОВОГО РЕЦЕПТА",
            description = " Рецепт добавляется формате:  1. Название;  2. Время приготовления;" +
                    "  3. Список и количество ингредиентов;  4. Шаги приготовления.")

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новый рецепт добавлен",
                            content = { @Content(
                                    mediaType = "application/jason",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                            }
                    )
            }
    )

    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {

        if (StringUtils.isBlank(recipe.getRecipeName())) {
            return ResponseEntity.badRequest().body("Не введено название рецепта!");
        }

        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "ПОИСК РЕЦЕПТА ПО ИДЕНТИФИКАТОРУ",
            description = "Необходимо указать идентификационный номер рецепта в списке")
    @Parameters(
            value = {
                    @Parameter (name = "id", example = "2"
                    )}
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Необходимый рецепт найден",
                            content = { @Content(
                                            mediaType = "application/jason",
                                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                            }
                    )
            }
    )
    public Recipe getRecipe(@PathVariable("id") long id) {
        return recipeService.get(id);
    }

    @GetMapping
    @Operation(summary = "СПИСОК ВСЕХ РЕЦЕПТОВ",
    description = "В списке указаны только добавленные рецепты")

    public List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "ВНЕСЕНИЕ ИЗМЕНЕНИЙ В РЕЦЕПТЫ",
            description = "Изменение рецепта, найденного по идентификатору из списка рецептов"
    )
    @Parameters(
            value = {
                    @Parameter (name = "id", example = "2"
                    )}
    )

    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "УДАЛЕНИЕ РЕЦЕПТА ИЗ СПИСКА",
            description = "Удаление рецепта, найденного по идентификатору из списка рецептов"
    )
    @Parameters(
            value = {
                    @Parameter (name = "id", example = "2"
                    )}
    )
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.delete(id);
    }
}


