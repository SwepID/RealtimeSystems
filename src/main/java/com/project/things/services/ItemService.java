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
        Category category = categoryRepository.findById(category_id).get();
        item.setCategory(category);
        return repo.save(item);
    }

    public Item getItemById(Integer id){
        return repo.findById(id).get();
    }
    public List<Item> getAllItems(){
        return repo.findAll();
    }
    public Item getItemByName(String name){
        return repo.findAll().stream().filter(elem -> elem.getName() == name).findFirst().get();
    }
    public Item updateItem(Integer item_id, String newName, String newDescription, Integer newCategoryId){
        Item item = repo.findById(item_id).get();
        item.setName(newName);
        item.setDescription(newDescription);
        Category category = categoryRepository.findById(newCategoryId).get();
        item.setCategory(category);
        return repo.save(item);
    }
    public void deleteItem(Integer item_id){
        repo.deleteById(item_id);
    }

}
