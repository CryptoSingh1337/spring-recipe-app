package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.services.IngredientService;
import com.saransh.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by CryptoSingh1337 on 7/2/2021
 */
@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}/ingredient")
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public String getIngredientsList(@PathVariable Long recipeId, Model model) {
        log.debug("Getting Ingredients for the Recipe Id: " + recipeId);
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "ingredient/list";
    }

    @GetMapping("/show")
    public String showRecipeIngredient(@PathVariable Long recipeId,
                                       @RequestParam("id") Long ingredientId,
                                       Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "ingredient/show";
    }
}
