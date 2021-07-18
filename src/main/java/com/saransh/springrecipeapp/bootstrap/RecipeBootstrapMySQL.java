package com.saransh.springrecipeapp.bootstrap;

import com.saransh.springrecipeapp.domain.Category;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import com.saransh.springrecipeapp.repositories.CategoryRepository;
import com.saransh.springrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by CryptoSingh1337 on 7/18/2021
 */
@Slf4j
@Component
@Profile({"prod", "dev"})
public class RecipeBootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrapMySQL(CategoryRepository categoryRepository,
                                UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (categoryRepository.count() == 0) {
            log.debug("Loading categories");
            loadCategory();
        }

        if (unitOfMeasureRepository.count() == 0) {
            log.debug("Loading UOM");
            loadUom();
        }
    }

    private void loadCategory() {
        Category category1 = new Category();
        category1.setDescription("American");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setDescription("Italian");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setDescription("Mexican");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category4.setDescription("Fast Food");
        categoryRepository.save(category4);
    }

    private void loadUom() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Each");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Dash");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Cup");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Pinch");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Ounce");
        unitOfMeasureRepository.save(uom7);
    }
}
