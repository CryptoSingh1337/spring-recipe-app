package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CryptoSingh1337 on 7/2/2021
 */
@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}/ingredient")
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/list")
    public String getIngredientsList(@PathVariable Long recipeId, Model model) {
        log.debug("Getting Ingredients for the Recipe Id: " + recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "ingredient/list";
    }
}
