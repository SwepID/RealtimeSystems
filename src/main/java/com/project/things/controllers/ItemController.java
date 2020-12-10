package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.services.ItemService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    Gson gson = new Gson();

    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public String createItem(@RequestParam(required = false, defaultValue = "some description")
                                     String description, @RequestParam(required = false,
            defaultValue = "some name") String name, @RequestParam Integer category_id){
        return gson.toJson(Hibernate.unproxy(itemService.createItem(name, description, category_id)));
    }

    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    public String getItemById(@PathVariable(value = "id") Integer id){
        return gson.toJson(Hibernate.unproxy(itemService.getItemById(id)));
    }
    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public String getAllItems(){
        return gson.toJson(Hibernate.unproxy(itemService.getAllItems()));
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public String updateItem(@RequestParam Integer id, @RequestParam String newDescription, @RequestParam String newName, @RequestParam Integer newCategoryId){
        return gson.toJson(Hibernate.unproxy(itemService.updateItem(id,newName, newDescription, newCategoryId)));
    }
    @RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE)
    public String deleteItemById(@RequestParam Integer id){
        return gson.toJson(Hibernate.unproxy(itemService.deleteItem(id)));
    }
}
