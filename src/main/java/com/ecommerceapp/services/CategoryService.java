package com.ecommerceapp.services;


import com.ecommerceapp.entitys.Category;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.repos.CategoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    public static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepo categoryRepo;

    public Category addOrSaveCategory(Category cat){
        return categoryRepo.save(cat);
    }
    public Category getCategoryById(Long id) throws EntityNotFoundException {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isPresent()) {
            Category myCategory = optionalCategory.get();
            return myCategory;
        } else {
            throw new EntityNotFoundException("Category not found!!!");
        }
    }

    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
