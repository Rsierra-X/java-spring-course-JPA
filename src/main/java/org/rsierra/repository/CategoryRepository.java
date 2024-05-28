package org.rsierra.repository;

import org.rsierra.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* CrudRepository set the crud essentials functions*/
/*
for version < 3.2
public interface CategoryRepository extends CrudRepository<Category, Integer>*/
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
