package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.NotesCommand;
import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.converters.*;
import com.saransh.springrecipeapp.domain.Notes;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,
                new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                        new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                        new NotesCommandToNotes()),
                new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                        new NotesToNotesCommand()));
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1, recipes.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.getRecipeById(1L);
        assertNotNull(returnedRecipe ,"Null Recipe Returned");
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void savedRecipeCommand() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        recipeCommand.setNotes(new NotesCommand());

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setNotes(new Notes());

        when(recipeRepository.save(any())).thenReturn(recipe);

        RecipeCommand savedRecipeCommand = recipeService.savedRecipeCommand(recipeCommand);
        assertNotNull(savedRecipeCommand);
        assertEquals(1L, savedRecipeCommand.getId());
        verify(recipeRepository, times(1)).save(any());
    }

    @Test
    void testFindCommandById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        RecipeCommand savedRecipeCommand = recipeService.findCommandById(1L);
        assertNotNull(savedRecipeCommand);
        assertEquals(1L, savedRecipeCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testDeleteRecipeById() {
        recipeService.deleteRecipeById(1L);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}