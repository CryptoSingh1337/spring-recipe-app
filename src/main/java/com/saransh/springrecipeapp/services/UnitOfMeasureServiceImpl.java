package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.saransh.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by CryptoSingh1337 on 7/3/2021
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand convertor;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand convertor) {
        this.uomRepository = uomRepository;
        this.convertor = convertor;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUom() {
        return StreamSupport.stream(
                uomRepository.findAll().spliterator(), false)
                .map(convertor::convert)
                .collect(Collectors.toSet());
    }
}
