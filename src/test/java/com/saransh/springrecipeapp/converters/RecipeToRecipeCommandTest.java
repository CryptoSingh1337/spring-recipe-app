package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    private RecipeToRecipeCommand recipeToCommannd;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";
    private final Integer PREP_TIME = 10;
    private final Integer COOK_TIME = 10;
    private final Integer SERVINGS = 4;
    private final String SOURCE = "source";
    private final String URL = "url";
    private final String DIRECTIONS = "directions";
    private final Difficulty DIFFICULTY = Difficulty.EASY;

    private final Long NOTES_ID = 2L;

    private final Long INGREDIENT_ID = 3L;

    private final Long CATEGORY_ID = 5L;

    @BeforeEach
    void setUp() {
        recipeToCommannd = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    void testNullObject() {
        assertNull(recipeToCommannd.convert(null));
    }

    @Test
    void testEmptyObject() {
        Recipe recipe = new Recipe();
        recipe.setNotes(new Notes());
        assertNotNull(recipeToCommannd.convert(recipe));
    }

    @Test
    void testWithNoCategory() {
        // given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(notes);
        recipe.getIngredients().add(ingredient);

        // when
        RecipeCommand command = recipeToCommannd.convert(recipe);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNotNull(command.getNotes());
        assertEquals(0, command.getCategories().size());
        assertEquals(1, command.getIngredients().size());
    }

    @Test
    void testWithNoIngredients() {
        // given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        Category category = new Category();
        category.setId(CATEGORY_ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(notes);
        recipe.getCategories().add(category);

        // when
        RecipeCommand command = recipeToCommannd.convert(recipe);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNotNull(command.getNotes());
        assertEquals(1, command.getCategories().size());
        assertEquals(0, command.getIngredients().size());
    }

    @Test
    void convert() {
        // given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);

        Category category = new Category();
        category.setId(CATEGORY_ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(notes);
        recipe.getIngredients().add(ingredient);
        recipe.getCategories().add(category);

        // when
        RecipeCommand command = recipeToCommannd.convert(recipe);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertNotNull(command.getNotes());
        assertEquals(1, command.getCategories().size());
        assertEquals(1, command.getIngredients().size());
    }
}