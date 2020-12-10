package com.project.things.services;

import com.google.gson.Gson;
import com.project.things.dao.ShopChainRepository;
import com.project.things.entities.ShopChain;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShopChainService {

    @Autowired
    private ShopChainRepository repo;

    public ShopChain createShopChain(String name, String website){
        ShopChain shopChain = new ShopChain();
        shopChain.setShopChainName(name);
        shopChain.setWebsite(website);
        repo.save(shopChain);
        return shopChain;
    }
    public ShopChain getShopChainById(Integer id){
        ShopChain shopChain = repo.getOne(id);
        return shopChain;
    }
    public ShopChain getShopChainByName(String name){
        ShopChain shopChain = repo.findAll().stream().filter(elem -> elem.getShopChainName() == name)
                .findFirst().get();
        return shopChain;
    }
    public List<ShopChain> getAllShopChains(){
        return repo.findAll();
    }

    public ShopChain updateShopChainById(Integer id, String newName, String newWebSite){
        ShopChain shopChain = repo.getOne(id);
        shopChain.setShopChainName(newName);
        shopChain.setWebsite(newWebSite);
        return repo.save(shopChain);
    }
    public String deleteShopChainById(Integer id){
        repo.deleteById(id);
        return "success";
    }
    public List<String> getAllShopsAndShopChains(){
        return repo.getAllShopsAndShopChains();
    }

}
