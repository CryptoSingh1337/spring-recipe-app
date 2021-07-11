package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.domain.Recipe;
import com.saransh.springrecipeapp.exceptions.NotFoundException;
import com.saransh.springrecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;
    private RecipeController controller;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void getRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(
                get("/recipe/show?id=1"))
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(status().isOk());
    }

    @Test
    void getRecipeByIdNotFound() throws Exception {
        when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/show?id=1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attributeExists("error_code"));
    }

    @Test
    void getRecipeByIdNumberFormat() throws Exception {
        when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/show?id=fafda"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/error"))
                .andExpect(model().attributeExists("exception"))
                .andExpect(model().attributeExists("error_code"));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {
        mockMvc.perform(
                get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.savedRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(
                post("/recipe/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "2")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show?id=2"));
    }

    @Test
    void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(
                get("/recipe/update?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipe-form"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testDeleteAction() throws Exception {
        mockMvc.perform(
                get("/recipe/delete?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteRecipeById(anyLong());
    }
}