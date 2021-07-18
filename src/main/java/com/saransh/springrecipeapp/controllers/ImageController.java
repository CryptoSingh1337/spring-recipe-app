package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.services.ImageService;
import com.saransh.springrecipeapp.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CryptoSingh1337 on 7/6/2021
 */
@Controller
@RequestMapping("/recipe/{id}/image")
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping({"", "/"})
    public String getImageForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "/recipe/image-upload-form";
    }

    @PostMapping({"", "/"})
    public String handleImagePost(@PathVariable Long id,
                                  @RequestParam("image")MultipartFile multipartFile) {
        imageService.saveImageFile(id, multipartFile);
        return "redirect:/recipe/show?id=" + id;
    }

    @GetMapping("/show")
    public void renderImageFromDatabase(@PathVariable Long id, HttpServletResponse res) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(id);
        if (recipeCommand.getImage() != null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;
            for (Byte b : recipeCommand.getImage())
                byteArray[i++] = b;

            res.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, res.getOutputStream());
        }
    }
}
