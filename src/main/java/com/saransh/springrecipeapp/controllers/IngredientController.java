package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.services.IngredientService;
import com.saransh.springrecipeapp.services.RecipeService;
import com.saransh.springrecipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CryptoSingh1337 on 7/2/2021
 */
@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}/ingredient")
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping("/update")
    public String updateIngredient(@PathVariable Long recipeId,
                               @RequestParam("id") Long ingredientId,
                               Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.listAllUom());
        return "ingredient/ingredient-form";
    }

    @GetMapping("/new")
    public String newRecipeIngredient(@PathVariable Long recipeId,
                                      Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        if (recipeCommand == null) {
            log.error("Recipe not found with Id: " + recipeId);
            throw new RuntimeException("Recipe not found");
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.listAllUom());
        return "ingredient/ingredient-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@PathVariable Long recipeId,
                               @ModelAttribute("ingredient") IngredientCommand command) {
        IngredientCommand ingredientCommand = ingredientService.saveIngredientCommand(recipeId, command);
        log.debug("Saved Recipe Id: " + ingredientCommand.getRecipeId());
        log.debug("Saved Ingredient Id: " + ingredientCommand.getId());
        return "redirect:/recipe/" + recipeId + "/ingredient/show?id=" + ingredientCommand.getId();
    }
}
