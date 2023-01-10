package com.example.ru_kam_recipeapp.controller;

import com.example.ru_kam_recipeapp.service.RecipeFilesService;
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
@RequestMapping("/files/recipeFiles")
public class RecipeFilesController {
    private final RecipeFilesService recipeFilesService;

    public RecipeFilesController(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadRecipeDataFile() {
        File recipeDataFile = recipeFilesService.getRecipeDataFile();

        if (recipeDataFile.exists()) {
            InputStreamResource recipeResourse = null;
            try {
                recipeResourse = new InputStreamResource(new FileInputStream(recipeDataFile));
            } catch (FileNotFoundException e) {
                throw new JsonMyException("Файл не найден!");
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                    .contentLength(recipeDataFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(recipeResourse);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeDataFile(@RequestParam MultipartFile recipeDataFile) {
        File recipeDataFile1 = recipeFilesService.getRecipeDataFile();
        recipeFilesService.cleanRecipeDataFile();

        try (FileOutputStream fos = new FileOutputStream(recipeDataFile1)) {
            IOUtils.copy(recipeDataFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

}
