package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getRecipeById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipe-form";
    }

    @GetMapping("/update")
    public String updateRecipe( @RequestParam("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipe-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.savedRecipeCommand(command);
        return "redirect:/recipe/show?id=" + savedCommand.getId();
    }
}
