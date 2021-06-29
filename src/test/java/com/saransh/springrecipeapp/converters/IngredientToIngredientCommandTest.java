package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.domain.Ingredient;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    private IngredientToIngredientCommand ingredientToCommand;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";
    private final BigDecimal AMOUNT = BigDecimal.ONE;
    private final Long UOM_ID = 2L;
    private final String UOM_DESCRIPTION = "uom_description";

    @BeforeEach
    void setUp() {
        ingredientToCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject() {
        assertNull(ingredientToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(ingredientToCommand.convert(new Ingredient()));
    }

    @Test
    void testWithNullUOM() {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        // when
        IngredientCommand ingredientCommand = ingredientToCommand.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNull(ingredientCommand.getUnitOfMeasure());
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(uom);

        // when
        IngredientCommand ingredientCommand = ingredientToCommand.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(UOM_DESCRIPTION, ingredientCommand.getUnitOfMeasure().getDescription());
    }
}