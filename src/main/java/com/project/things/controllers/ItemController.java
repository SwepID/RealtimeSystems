package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.entities.Item;
import com.project.things.services.ItemService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public Item createItem(@RequestParam(required = false, defaultValue = "some description")
                                     String description, @RequestParam(required = false,
            defaultValue = "some name") String name, @RequestParam Integer category_id){
        return itemService.createItem(name, description, category_id);
    }

    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    public Item getItemById(@PathVariable(value = "id") Integer id){
        return itemService.getItemById(id);
    }
    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public Item updateItem(@RequestParam Integer id, @RequestParam String newDescription, @RequestParam String newName, @RequestParam Integer newCategoryId){
        return itemService.updateItem(id,newName, newDescription, newCategoryId);
    }
    @RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE)
    public void deleteItemById(@RequestParam Integer id){
        itemService.deleteItem(id);
    }
}
