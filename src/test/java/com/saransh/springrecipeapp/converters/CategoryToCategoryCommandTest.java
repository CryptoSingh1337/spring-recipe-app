package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.CategoryCommand;
import com.saransh.springrecipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    private CategoryToCategoryCommand categoryToCommand;
    private final Long ID = 1L;
    private final String DESCRIPTION = "description";

    @BeforeEach
    void setUp() {
        categoryToCommand = new CategoryToCategoryCommand();
    }

    @Test
    void testNull() {
        assertNull(categoryToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(categoryToCommand.convert(new Category()));
    }

    @Test
    void convert() {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        // when
        CategoryCommand categoryCommand = categoryToCommand.convert(category);

        // then
        assertNotNull(categoryCommand);
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}