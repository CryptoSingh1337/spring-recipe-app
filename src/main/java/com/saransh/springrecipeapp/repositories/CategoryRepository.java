package com.saransh.springrecipeapp.repositories;

import com.saransh.springrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CryptoSingh1337 on 6/16/2021
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
