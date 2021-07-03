package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import com.saransh.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureServiceImpl unitOfMeasureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void listAllUom() {
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();
        UnitOfMeasure uom_1 = new UnitOfMeasure();
        uom_1.setId(1L);

        UnitOfMeasure uom_2 = new UnitOfMeasure();
        uom_2.setId(2L);

        unitOfMeasureSet.add(uom_1);
        unitOfMeasureSet.add(uom_2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureSet);

        Set<UnitOfMeasureCommand> uomCommands = unitOfMeasureService.listAllUom();

        assertEquals(2, uomCommands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}