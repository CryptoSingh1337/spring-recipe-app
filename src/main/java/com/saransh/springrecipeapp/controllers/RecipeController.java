package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.exceptions.NotFoundException;
import com.saransh.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String updateRecipe( @RequestParam("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipe-form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.savedRecipeCommand(command);
        return "redirect:/recipe/show?id=" + savedCommand.getId();
    }

    @GetMapping("/delete")
    public String deleteRecipe(@RequestParam("id") Long id) {
        log.debug("Deleting Recipe id: " + id);
        recipeService.deleteRecipeById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling not found exception");
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("error_code", "404");
        mav.addObject("exception", e);
        return mav;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException() {
        log.error("Handling Number Format exception");
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("error_code", "400");
        mav.addObject("exception", new RuntimeException("Invalid ID. ID must be a Number"));
        return mav;
    }
}
