package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.NotesCommand;
import com.saransh.springrecipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    private NotesCommandToNotes commandToNotes;
    private final Long ID = 1L;
    private final String RECIPE_NOTES = "recipe notes";

    @BeforeEach
    void setUp() {
        commandToNotes = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(commandToNotes.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(commandToNotes.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        // given
        NotesCommand command = new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes notes = commandToNotes.convert(command);

        // then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}