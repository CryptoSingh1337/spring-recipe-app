package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.converters.IngredientToIngredientCommand;
import com.saransh.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.Ingredient;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository,
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient_1 = new Ingredient();
        ingredient_1.setId(1L);

        Ingredient ingredient_2 = new Ingredient();
        ingredient_2.setId(2L);

        Ingredient ingredient_3 = new Ingredient();
        ingredient_3.setId(3L);

        recipe.addIngredient(ingredient_1);
        recipe.addIngredient(ingredient_2);
        recipe.addIngredient(ingredient_3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 1L);

        assertEquals(1L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}