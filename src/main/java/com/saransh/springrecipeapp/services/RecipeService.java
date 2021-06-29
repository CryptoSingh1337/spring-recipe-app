package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.domain.Recipe;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/20/2021
 */

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
    RecipeCommand savedRecipeCommand(RecipeCommand recipeCommand);
}
