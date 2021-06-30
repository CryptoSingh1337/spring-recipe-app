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
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConvertor;
    private final IngredientCommandToIngredient ingredientConvertor;
    private final NotesCommandToNotes notesConvertor;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConvertor,
                                 IngredientCommandToIngredient ingredientConvertor,
                                 NotesCommandToNotes notesConvertor) {
        this.categoryConvertor = categoryConvertor;
        this.ingredientConvertor = ingredientConvertor;
        this.notesConvertor = notesConvertor;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null)
            return null;
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());

        if (source.getNotes() != null)
            recipe.setNotes(notesConvertor.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0)
            source.getCategories().forEach(categoryCommand -> recipe.getCategories()
                    .add(categoryConvertor.convert(categoryCommand)));

        if (source.getIngredients() != null && source.getIngredients().size() > 0)
            source.getIngredients().forEach(ingredientCommand -> recipe.getIngredients()
                    .add(ingredientConvertor.convert(ingredientCommand)));
        return recipe;
    }
}
