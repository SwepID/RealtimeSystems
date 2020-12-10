package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.services.CategoryService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    Gson gson = new Gson();

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(@RequestParam(required = false, defaultValue = "category_name") String name,
                                 @RequestParam Integer shop_id){
        return gson.toJson(Hibernate.unproxy(categoryService.createCategory(shop_id, name)));
    }

    @RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
    public String getCategoryById(@PathVariable(value = "id") Integer id){
        return gson.toJson(Hibernate.unproxy(categoryService.getCategory(id)));
    }
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public String getAllCategories(){
        return gson.toJson(Hibernate.unproxy(categoryService.getAllCategories()));
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
    public String updateCategoryById(@RequestParam Integer id, @RequestParam Integer newShopId,
                                     @RequestParam(required = false, defaultValue = "newName")
                                             String newName){
        return gson.toJson(Hibernate.unproxy(categoryService.updateCategory(id, newShopId, newName)));
    }
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
    public String deleteCategory(@RequestParam Integer id){
        return gson.toJson(Hibernate.unproxy(categoryService.deleteCategory(id)));
    }
}
