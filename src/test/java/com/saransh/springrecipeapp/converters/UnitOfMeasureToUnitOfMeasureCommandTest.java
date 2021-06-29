package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    private UnitOfMeasureToUnitOfMeasureCommand uomToCommand;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";

    @BeforeEach
    void setUp() {
        uomToCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullObject() {
        assertNull(uomToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(uomToCommand.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand command = uomToCommand.convert(uom);

        // then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}