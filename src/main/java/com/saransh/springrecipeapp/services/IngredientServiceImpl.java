package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.converters.IngredientCommandToIngredient;
import com.saransh.springrecipeapp.converters.IngredientToIngredientCommand;
import com.saransh.springrecipeapp.domain.Ingredient;
import com.saransh.springrecipeapp.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

/**
 * Created by CryptoSingh1337 on 7/2/2021
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand convertor;
    private final IngredientCommandToIngredient convertorReversed;

    public IngredientServiceImpl(
            IngredientRepository ingredientRepository,
            IngredientToIngredientCommand convertor,
            IngredientCommandToIngredient convertorReversed) {
        this.ingredientRepository = ingredientRepository;
        this.convertor = convertor;
        this.convertorReversed = convertorReversed;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        return convertor.convert(
                ingredientRepository.findByRecipeIdAndIngredientId(
                        recipeId,
                        ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with Id: " + ingredientId)));
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(Long recipeId, IngredientCommand command) {
        Ingredient savedIngredient = ingredientRepository.save(
                Objects.requireNonNull(convertorReversed.convert(command)));
        return convertor.convert(savedIngredient);
    }

    @Override
    @Transactional
    public void deleteById(Long recipeId, Long ingredientId) {
        ingredientRepository.deleteByIngredientIdAndRecipeId(recipeId, ingredientId);
    }
}
