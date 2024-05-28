package org.rsierra;

import org.rsierra.models.Category;
import org.rsierra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    private void saveAllCategories(){
        List<Category> categories = getListaCategorias();
        categoryRepository.saveAll(categories);
    }

    private void exitedId(){
        boolean exists = categoryRepository.existsById(1);
        if(exists){
            categoryRepository.deleteById(1);
        }
    }

    private void findAllCategoriesSorted(){
        List<Category> categories = categoryRepository.findAll(Sort.by("name").descending());
        for(Category category : categories){
            System.out.println(category);
        }
    }

    private void findAllCategoriesPagination(){
        Page<Category> categoriesPage = categoryRepository.findAll(PageRequest.of(1,5));
        System.out.println("Totoal de paginas"+ categoriesPage.getTotalPages());
        System.out.println("Totoal de elements"+ categoriesPage.getTotalElements());
        for(Category category : categoriesPage.getContent()){
            System.out.println(category);
        }
    }

    private void findAllCategories(){
        /*Iterable<Category> categories = categoryRepository.findAll();*/
        List<Category> categories = categoryRepository.findAll();
        for(Category category : categories){
            System.out.println(category);
        }
    }

     private void findAllById(){
         List<Integer> ids = new LinkedList<>();
         ids.add(1);
         ids.add(2);

         Iterable<Category> categories = categoryRepository.findAllById(ids);
         categories.forEach(System.out::println);
    }

    private void deleteAllCategories() {
        /*categoryRepository.deleteAll();*/
        //This function delete all, take care
        categoryRepository.deleteAllInBatch();
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

    private List<Category> getListaCategorias(){
        List<Category> lista = new LinkedList<Category>();
        // Categoria 1
        Category cat1 = new Category();
        cat1.setName("Programador de Blockchain");
        cat1.setDescription("Trabajos relacionados con Bitcoin y Criptomonedas");

        // Categoria 2
        Category cat2 = new Category();
        cat2.setName("Soldador/Pintura");
        cat2.setDescription("Trabajos relacionados con soldadura, pintura y enderezado");

        // Categoria 3
        Category cat3 = new Category();
        cat3.setName("Ingeniero Industrial");
        cat3.setDescription("Trabajos relacionados con Ingenieria industrial.");

        lista.add(cat1);
        lista.add(cat2);
        lista.add(cat3);
        return lista;
    }
}
