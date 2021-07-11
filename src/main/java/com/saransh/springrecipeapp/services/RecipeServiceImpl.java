package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.converters.RecipeCommandToRecipe;
import com.saransh.springrecipeapp.converters.RecipeToRecipeCommand;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.exceptions.NotFoundException;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/20/2021
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe commandToRecipe;
    private final RecipeToRecipeCommand recipeToCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe commandToRecipe,
                             RecipeToRecipeCommand recipeToCommand) {
        this.recipeRepository = recipeRepository;
        this.commandToRecipe = commandToRecipe;
        this.recipeToCommand = recipeToCommand;
    }

    @Override
    @Transactional
    public Set<Recipe> getRecipes() {
        log.debug("Getting all the Recipes from Database");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    @Transactional
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found"));
    }

    @Override
    @Transactional
    public RecipeCommand savedRecipeCommand(RecipeCommand recipeCommand) {
        if (recipeCommand == null)
            throw new RuntimeException("Recipe Command is null");
        Recipe detachedRecipe = commandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe ID: " + savedRecipe.getId());
        return recipeToCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToCommand.convert(getRecipeById(id));
    }

    @Override
    @Transactional
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
}
