package org.rsierra.repository;

import org.rsierra.models.Category;
import org.springframework.data.repository.CrudRepository;

/*
* CrudRepository set the crud essentials functions*/
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
