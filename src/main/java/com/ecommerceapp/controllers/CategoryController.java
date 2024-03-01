package com.ecommerceapp.controllers;


import com.ecommerceapp.entitys.Category;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.services.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
        categoryService.addOrSaveCategory(category);
        logger.info(category.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }


    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        logger.info(allCategories.toString());
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody @Valid Category category) throws EntityNotFoundException {
        Category existingCategory = categoryService.getCategoryById(id);
        existingCategory.setCat_name(category.getCat_name());
        categoryService.addOrSaveCategory(existingCategory);
        return ResponseEntity.ok("Category updated successfully");
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) throws EntityNotFoundException {
       Category myCategory = categoryService.getCategoryById(id);
       categoryService.deleteCategory(id);
       return ResponseEntity.ok("Category deleted successfully");
    }
}
