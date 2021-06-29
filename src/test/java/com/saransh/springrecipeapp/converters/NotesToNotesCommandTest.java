package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.NotesCommand;
import com.saransh.springrecipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    private NotesToNotesCommand notesToCommand;
    private final Long ID = 1L;
    private final String RECIPE_NOTES = "recipe notes";

    @BeforeEach
    void setUp() {
        notesToCommand = new NotesToNotesCommand();
    }

    @Test
    void testNullObject() {
        assertNull(notesToCommand.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(notesToCommand.convert(new Notes()));
    }

    @Test
    void convert() {
        // given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        // when
        NotesCommand notesCommand = notesToCommand.convert(notes);

        // then
        assertNotNull(notesCommand);
        assertEquals(ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}