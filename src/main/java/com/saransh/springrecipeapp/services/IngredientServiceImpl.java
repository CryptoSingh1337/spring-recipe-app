package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.converters.IngredientToIngredientCommand;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by CryptoSingh1337 on 7/2/2021
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand convertor;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand convertor) {
        this.recipeRepository = recipeRepository;
        this.convertor = convertor;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty())
            log.error("Recipe not found with Id: " + recipeId);

        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(convertor::convert).findFirst();

        if (ingredientCommandOptional.isEmpty())
            log.error("Ingredient not found with Id: " + ingredientId);
        return ingredientCommandOptional.get();
    }
}
