package org.rsierra;

import org.rsierra.models.Category;
import org.rsierra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        findAllById();
    }

     private void findAllById(){
         List<Integer> ids = new LinkedList<>();
         ids.add(1);
         ids.add(2);

         Iterable<Category> categories = categoryRepository.findAllById(ids);
         categories.forEach(System.out::println);
    }

    private void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    private void countCategories(){
        long count = categoryRepository.count();
        System.out.println(count);
    }

    private void deleteCategory(){
        int idCategory = 1;
        categoryRepository.deleteById(idCategory);
    }

    private void updateCategory() {
        Optional<Category> category = categoryRepository.findById(1);
        if(category.isPresent()){
            Category auxCategory = category.get();
            auxCategory.setName("New Name");
            auxCategory.setDescription("New description");
            categoryRepository.save(auxCategory);
            System.out.println(category.get().getName());
        } else {
            System.out.println("Categoria no encontrada");
        }
    }

    private void searchById(){
        Optional<Category> category = categoryRepository.findById(1);
        if(category.isPresent()){
            System.out.println(category.get().getName());
        } else {
            System.out.println("Categoria no encontrada");
        }
    }

    private void save(){
        Category category = new Category();
        category.setName("JPA Demo");
        category.setDescription("JPA Demo");
        categoryRepository.save(category);
    }

    private void delete(){
        System.out.println("deleted");
    }
}
