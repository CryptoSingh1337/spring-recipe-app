package com.saransh.springrecipeapp.services;

import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 7/3/2021
 */

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUom();
}
