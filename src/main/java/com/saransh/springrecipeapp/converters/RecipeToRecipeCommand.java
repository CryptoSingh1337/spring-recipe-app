package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by CryptoSingh1337 on 6/29/2021
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConvertor;
    private final IngredientToIngredientCommand ingredientConvertor;
    private final NotesToNotesCommand notesConvertor;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConvertor,
                                 IngredientToIngredientCommand ingredientConvertor,
                                 NotesToNotesCommand notesConvertor) {
        this.categoryConvertor = categoryConvertor;
        this.ingredientConvertor = ingredientConvertor;
        this.notesConvertor = notesConvertor;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesConvertor.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0)
            source.getCategories().forEach(category -> recipeCommand.getCategories()
                    .add(categoryConvertor.convert(category)));

        if (source.getIngredients() != null && source.getIngredients().size() > 0)
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients()
                    .add(ingredientConvertor.convert(ingredient)));
        return recipeCommand;
    }
}
