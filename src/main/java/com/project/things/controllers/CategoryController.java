package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.entities.Category;
import com.project.things.services.CategoryService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public Category createCategory(@RequestParam(required = false, defaultValue = "category_name") String name,
                                   @RequestParam Integer shop_id){
        return categoryService.createCategory(shop_id, name);
    }

    @RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable(value = "id") Integer id){
        return categoryService.getCategory(id);
    }
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
    public Category updateCategoryById(@RequestParam Integer id, @RequestParam Integer newShopId,
                                     @RequestParam(required = false, defaultValue = "newName")
                                             String newName){
        return categoryService.updateCategory(id, newShopId, newName);
    }
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
    public void deleteCategory(@RequestParam Integer id){
        categoryService.deleteCategory(id);
    }
}
