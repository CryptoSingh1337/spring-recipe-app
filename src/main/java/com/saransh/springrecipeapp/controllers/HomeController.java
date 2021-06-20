package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.domain.Category;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import com.saransh.springrecipeapp.repositories.CategoryRepository;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import com.saransh.springrecipeapp.repositories.UnitOfMeasureRepository;
import com.saransh.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by CryptoSingh1337 on 6/13/2021
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
