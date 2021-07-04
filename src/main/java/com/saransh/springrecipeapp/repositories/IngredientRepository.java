package com.saransh.springrecipeapp.repositories;

import com.saransh.springrecipeapp.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by CryptoSingh1337 on 7/4/2021
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("select i from Ingredient i where i.id=:ingredient_id and i.recipe.id=:recipe_id")
    Optional<Ingredient> findByRecipeIdAndIngredientId(@Param("recipe_id") Long recipeId,
                                                       @Param("ingredient_id") Long ingredientId);

    @Query("delete from Ingredient i where i.id=:ingredient_id and i.recipe.id=:recipe_id")
    @Modifying
    void deleteByIngredientIdAndRecipeId(@Param("recipe_id") Long recipeId,
                                         @Param("ingredient_id") Long ingredientId);
}
