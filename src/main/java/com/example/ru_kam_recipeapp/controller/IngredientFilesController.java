package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.service.IngredientFilesService;
import com.example.ru_kam_recipeapp.service.myException.JsonMyException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/ingredientFiles")
public class IngredientFilesController {
    private final IngredientFilesService ingredientFilesService;

    public IngredientFilesController(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadIngredientDataFile() {
        File ingredientDataFile = ingredientFilesService.getIngredientDataFile();

        if (ingredientDataFile.exists()) {
            InputStreamResource ingredientResourse = null;
            try {
                ingredientResourse = new InputStreamResource(new FileInputStream(ingredientDataFile));
            } catch (FileNotFoundException e) {
                throw new JsonMyException("Файл не найден!");
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                    .contentLength(ingredientDataFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientsLog.json\"")
                    .body(ingredientResourse);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientDataFile(@RequestParam MultipartFile ingredientDataFile) {
        File ingredientDataFile1 = ingredientFilesService.getIngredientDataFile();
        ingredientFilesService.cleanIngredientDataFile();

        try (FileOutputStream fos = new FileOutputStream(ingredientDataFile1)) {
            IOUtils.copy(ingredientDataFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}

