package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by CryptoSingh1337 on 7/6/2021
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile imageFile) {
        log.debug("Received an image file");
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with Id: " + recipeId));
        try {
            int length = imageFile.getBytes().length;
            Byte[] file = new Byte[length];
            int i = 0;
            for (byte b : imageFile.getBytes())
                file[i++] = b;

            recipe.setImage(file);
            recipeRepository.save(recipe);
            log.debug("Image saved successfully to Recipe with Id: " + recipeId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
