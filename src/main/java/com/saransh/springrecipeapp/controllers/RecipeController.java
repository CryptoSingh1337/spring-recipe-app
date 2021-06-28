package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by CryptoSingh1337 on 6/27/2021
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/show")
    public String getRecipeById(Model model, @RequestParam("id") Long id) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe/show";
    }
}
