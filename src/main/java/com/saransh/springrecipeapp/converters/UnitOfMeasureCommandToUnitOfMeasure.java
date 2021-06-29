package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.UnitOfMeasureCommand;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by CryptoSingh1337 on 6/29/2021
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure  implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null)
            return null;
        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}
