package com.saransh.springrecipeapp.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by CryptoSingh1337 on 7/6/2021
 */

public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile imageFile);
}
