package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private UnitOfMeasureCommandToUnitOfMeasure commandToUom;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";

    @BeforeEach
    void setUp() {
        commandToUom = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullObject() {
        assertNull(commandToUom.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToUom.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        // when
        UnitOfMeasure uom = commandToUom.convert(command);

        // then
        assertNotNull(uom);
        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}