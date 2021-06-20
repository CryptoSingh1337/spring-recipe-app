package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.domain.Category;
import com.saransh.springrecipeapp.domain.UnitOfMeasure;
import com.saransh.springrecipeapp.repositories.CategoryRepository;
import com.saransh.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by CryptoSingh1337 on 6/13/2021
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public HomeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        categoryOptional.ifPresent(e -> System.out.println("Cat Id is: " + e.getId()));
        uomOptional.ifPresent(e -> System.out.println("UOM Id is: " + e.getId()));
        return "index";
    }
}
