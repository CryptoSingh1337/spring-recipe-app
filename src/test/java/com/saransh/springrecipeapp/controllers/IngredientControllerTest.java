package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.services.IngredientService;
import com.saransh.springrecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private IngredientService ingredientService;
    private IngredientController controller;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getIngredientsList() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(
                get("/recipe/1/ingredient/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("ingredient/list"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void showRecipeIngredient() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
                .thenReturn(command);

        mockMvc.perform(
                get("/recipe/1/ingredient/show?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
}