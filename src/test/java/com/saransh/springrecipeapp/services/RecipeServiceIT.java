package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.converters.RecipeCommandToRecipe;
import com.saransh.springrecipeapp.converters.RecipeToRecipeCommand;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by CryptoSingh1337 on 6/29/2021
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeToRecipeCommand recipeToCommand;
    @Autowired
    private RecipeCommandToRecipe commandToRecipe;

    private final String DESCRIPTION = "description";

    @Test
    @Transactional
    void testSaveOfDescription() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToCommand.convert(testRecipe);

        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.savedRecipeCommand(testRecipeCommand);

        assertNotNull(savedRecipeCommand);
        assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
