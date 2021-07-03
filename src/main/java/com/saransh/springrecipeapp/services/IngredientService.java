package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.IngredientCommand;

/**
 * Created by CryptoSingh1337 on 7/2/2021
 */

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(Long recipeId, IngredientCommand command);
}
