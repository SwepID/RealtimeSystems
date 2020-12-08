package com.project.things.services;

import com.project.things.dao.CategoryRepository;
import com.project.things.dao.ItemRepository;
import com.project.things.entities.Category;
import com.project.things.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepository repo;

    @Autowired
    CategoryRepository categoryRepository;

    public Item createItem(String name, String description, Integer category_id){
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        Category category = categoryRepository.getOne(category_id);
        item.setCategory(category);
        return repo.save(item);
    }

    public Item getItemById(Integer id){
        return repo.getOne(id);
    }
    public List<Item> getAllItems(){
        return repo.findAll();
    }
    public Item getItemByName(String name){
        return repo.findAll().stream().filter(elem -> elem.getName() == name).findFirst().get();
    }
    public Item updateItem(Integer item_id, String newName, String newDescription, Integer newCategoryId){
        Item item = repo.getOne(item_id);
        item.setName(newName);
        item.setDescription(newDescription);
        Category category = categoryRepository.getOne(newCategoryId);
        item.setCategory(category);
        return repo.save(item);
    }
    public String deleteItem(Integer item_id){
        repo.deleteById(item_id);
        return "success";
    }

}
