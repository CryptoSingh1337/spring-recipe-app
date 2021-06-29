package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.CategoryCommand;
import com.saransh.springrecipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory commandToCategory;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";

    @BeforeEach
    void setUp() {
        commandToCategory = new CategoryCommandToCategory();
    }

    @Test
    void testNull() {
        assertNull(commandToCategory.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToCategory.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        // given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        // when
        Category category = commandToCategory.convert(command);

        // then
        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}