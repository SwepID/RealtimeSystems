package com.project.things.services;

import com.project.things.dao.CategoryRepository;
import com.project.things.dao.ShopRepository;
import com.project.things.entities.Category;
import com.project.things.entities.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository repo;

    @Autowired
    ShopRepository shopRepository;

    public Category createCategory(Integer shop_id, String name){
        Shop shop = shopRepository.findById(shop_id).get();
        Category category = new Category();
        category.setName(name);
        category.setShop(shop);
        return repo.save(category);
    }

    public Category getCategory(Integer category_id){
        return repo.findById(category_id).get();
    }

    public Category getCategoryByName(String name){
        Category category = repo.findAll().stream().filter(elem -> elem.getName() == name)
                .findFirst().get();
        return category;
    }

    public List<Category> getAllCategories(){
        return repo.findAll();
    }

    public Category updateCategory(Integer category_id, Integer newShopId, String newName){
        Category category = repo.findById(category_id).get();
        Shop shop = shopRepository.findById(newShopId).get();
        category.setShop(shop);
        category.setName(newName);
        return repo.save(category);
    }

    public void deleteCategory(Integer category_id){
        repo.deleteById(category_id);
    }
}
