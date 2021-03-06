package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by CryptoSingh1337 on 6/27/2021
 */
@Slf4j
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
    public String updateRecipe(@RequestParam("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipe-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command,
                               BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors()
                    .forEach(objectError -> log.debug(objectError.toString()));
            return "recipe/recipe-form";
        }
        RecipeCommand savedCommand = recipeService.savedRecipeCommand(command);
        return "redirect:/recipe/show?id=" + savedCommand.getId();
    }

    @GetMapping("/delete")
    public String deleteRecipe(@RequestParam("id") Long id) {
        log.debug("Deleting Recipe id: " + id);
        recipeService.deleteRecipeById(id);
        return "redirect:/";
    }
}
