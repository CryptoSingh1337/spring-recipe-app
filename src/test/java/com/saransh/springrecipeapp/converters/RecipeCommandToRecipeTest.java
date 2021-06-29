package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.CategoryCommand;
import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.commands.NotesCommand;
import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.domain.Difficulty;
import com.saransh.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private RecipeCommandToRecipe commandToRecipe;
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
        commandToRecipe = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    void testNullObject() {
        assertNull(commandToRecipe.convert(null));
    }

    @Test
    void testEmptyObject() {
        RecipeCommand recipe = new RecipeCommand();
        recipe.setNotes(new NotesCommand());
        assertNotNull(commandToRecipe.convert(recipe));
    }

    @Test
    void testWithNoCategory() {
        // given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);

        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);
        command.setNotes(notesCommand);
        command.getIngredients().add(ingredientCommand);

        // when
        Recipe recipe = commandToRecipe.convert(command);

        // then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(0, recipe.getCategories().size());
        assertEquals(1, recipe.getIngredients().size());
    }

    @Test
    void testWithNoIngredients() {
        // given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);

        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);
        command.setNotes(notesCommand);
        command.getCategories().add(categoryCommand);

        // when
        Recipe recipe = commandToRecipe.convert(command);

        // then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(0, recipe.getIngredients().size());
    }

    @Test
    void convert() {
        // given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);

        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);
        command.setNotes(notesCommand);
        command.getIngredients().add(ingredientCommand);
        command.getCategories().add(categoryCommand);

        // when
        Recipe recipe = commandToRecipe.convert(command);

        // then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getNotes());
        assertEquals(1, recipe.getCategories().size());
        assertEquals(1, recipe.getIngredients().size());
    }
}