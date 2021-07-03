package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.commands.IngredientCommand;
import com.saransh.springrecipeapp.commands.RecipeCommand;
import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.services.IngredientService;
import com.saransh.springrecipeapp.services.RecipeService;
import com.saransh.springrecipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private IngredientService ingredientService;
    @Mock
    private UnitOfMeasureService unitOfMeasureService;
    private IngredientController controller;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
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

    @Test
    void updateIngredient() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(1L);

        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = new HashSet<>();

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
        when(unitOfMeasureService.listAllUom()).thenReturn(unitOfMeasureCommandSet);

        mockMvc.perform(
                get("/recipe/1/ingredient/update?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/ingredient-form"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    void newRecipeIngredient() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUom()).thenReturn(new HashSet<>());

        mockMvc.perform(
                get("/recipe/1/ingredient/new?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/ingredient-form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    void saveOrUpdate() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(1L);

        when(ingredientService.saveIngredientCommand(anyLong(), any()))
                .thenReturn(command);

        mockMvc.perform(
                post("/recipe/1/ingredient/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredient/show?id=1"));
    }
}