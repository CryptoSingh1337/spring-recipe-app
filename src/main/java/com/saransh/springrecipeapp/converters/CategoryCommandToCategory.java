package com.saransh.springrecipeapp.converters;

import com.saransh.springrecipeapp.commands.CategoryCommand;
import com.saransh.springrecipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by CryptoSingh1337 on 6/29/2021
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null)
            return null;
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
