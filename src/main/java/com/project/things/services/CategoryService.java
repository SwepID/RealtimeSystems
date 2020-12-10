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
        Shop shop = shopRepository.getOne(shop_id);
        Category category = new Category();
        category.setName(name);
        category.setShop(shop);
        return repo.save(category);
    }

    public Category getCategory(Integer category_id){
        return repo.getOne(category_id);
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
        Category category = repo.getOne(category_id);
        Shop shop = shopRepository.getOne(newShopId);
        category.setShop(shop);
        category.setName(newName);
        return repo.save(category);
    }

    public String deleteCategory(Integer category_id){
        repo.deleteById(category_id);
        return "success";
    }
}
