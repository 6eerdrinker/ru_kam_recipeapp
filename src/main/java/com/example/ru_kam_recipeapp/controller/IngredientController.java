package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.model.Ingredient;
import com.example.ru_kam_recipeapp.model.Recipe;
import com.example.ru_kam_recipeapp.service.IngredientService;
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
@RequestMapping("/ingredient")
@Tag(name = "ИНГРЕДИЕНТЫ", description = "Возможность добавления нового ингредиента, " +
        "просмотра списка ингредиентов, просмотр, редактирование и удаление ингредиента по идентификатору")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "ДОБАВЛЕНИЕ НОВОГО ИНГРЕДИЕНТА",
            description = " Ингредиент добавляется формате:  1. Название;  2. Количество;" +
                    "  3. Единицы измерения.")

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новый ингредиент добавлен",
                            content = { @Content(
                                    mediaType = "application/jason",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                            }
                    )
            }
    )
    public ResponseEntity<?> addIngredient (@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getIngredientName())) {
            return ResponseEntity.badRequest().body("Не введено название ингредиента!");
        }

        return ResponseEntity.ok(ingredientService.add(ingredient));
    }


    @GetMapping("/{id}")
    @Operation(summary = "ПОИСК ИНГРЕДИЕНТА ПО ИДЕНТИФИКАТОРУ",
            description = "Необходимо указать идентификационный номер ингредиента в списке")
    @Parameters(
            value = {
                    @Parameter(name = "id", example = "2"
                    )}
    )

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Необходимый ингредиент найден",
                            content = { @Content(
                                    mediaType = "application/jason",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))
                            }
                    )
            }
    )
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return ingredientService.get(id);
    }

    @GetMapping
    @Operation(summary = "СПИСОК ВСЕХ ИНГРЕДИЕНТОВ",
            description = "В списке указаны только добавленные ингредиенты")
    public List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "ВНЕСЕНИЕ ИЗМЕНЕНИЙ В ИНГРЕДИЕНТЫ",
            description = "Изменение ингредиента, найденного по идентификатору из списка ингредиентов"
    )
    @Parameters(
            value = {
                    @Parameter (name = "id", example = "2"
                    )}
    )
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "УДАЛЕНИЕ ИНГРЕДИЕНТА ИЗ СПИСКА",
            description = "Удаление ингредиента, найденного по идентификатору из списка ингредиентов"
    )
    @Parameters(
            value = {
                    @Parameter (name = "id", example = "2"
                    )}
    )
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.delete(id);
    }
}
