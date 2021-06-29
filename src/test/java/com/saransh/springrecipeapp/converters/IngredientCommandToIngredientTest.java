package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    private IngredientCommandToIngredient commandToIngredient;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";
    private final BigDecimal AMOUNT = BigDecimal.ONE;
    private final Long UOM_ID = 2L;
    private final String UOM_DESCRIPTION = "uom_description";

    @BeforeEach
    void setUp() {
        commandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullObject() {
        assertNull(commandToIngredient.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    void testWithNullUOM() {
        // given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);

        // when
        Ingredient ingredient = commandToIngredient.convert(command);

        // then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNull(ingredient.getUom());
    }

    @Test
    void convert() {
        // given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);

        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        command.setUnitOfMeasure(uomCommand);

        // when
        Ingredient ingredient = commandToIngredient.convert(command);

        // then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUom().getDescription());
    }
}