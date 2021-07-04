package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.converters.IngredientCommandToIngredient;
import com.saransh.springrecipeapp.converters.IngredientToIngredientCommand;
import com.saransh.springrecipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.saransh.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.Ingredient;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.repositories.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(
                ingredientRepository,
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        when(ingredientRepository
                .findByRecipeIdAndIngredientId(anyLong(), anyLong()))
                .thenReturn(Optional.of(ingredient));

        IngredientCommand ingredientCommand = ingredientService
                .findByRecipeIdAndIngredientId(1L, 1L);

        assertNotNull(ingredientCommand);
        assertEquals(1L, ingredientCommand.getId());
    }

    @Test
    void testSaveIngredientCommand() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setAmount(BigDecimal.ONE);
        ingredient.setDescription("description");
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        ingredient.setRecipe(recipe);

        when(ingredientRepository.save(any())).thenReturn(ingredient);

        IngredientCommand savedCommand = ingredientService
                .saveIngredientCommand(1L, new IngredientCommand());

        assertNotNull(savedCommand);
        assertEquals(1L, savedCommand.getId());
        assertEquals(1L, savedCommand.getRecipeId());
    }

    @Test
    void testDeleteIngredientById() {
        ingredientService.deleteById(1L, 1L);

        verify(ingredientRepository, times(1))
                .deleteByIngredientIdAndRecipeId(anyLong(), anyLong());
    }
}