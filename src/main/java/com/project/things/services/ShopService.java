package com.project.things.services;

import com.project.things.dao.ShopChainRepository;
import com.project.things.dao.ShopRepository;
import com.project.things.entities.Shop;
import com.project.things.entities.ShopChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.annotation.ElementType;
import java.util.List;


@Service
@Transactional
public class ShopService {

    @Autowired private ShopRepository repo;
    @Autowired private ShopChainRepository shopChainRepo;
    
    /*public String createShopChain(String name){
        Shop shop = new Shop();

        return "Создан!";
    }*/
    public String createShop(Integer shopChain_id, String contacts){
        Shop shop = new Shop();
        ShopChain shopChain = shopChainRepo.getOne(shopChain_id);
        shop.setShopChain(shopChain);
        shop.setContacts(contacts);
        repo.save(shop);
        return  shop.toString();
    }

    public Shop getShopById(Integer shop_id){
        return repo.getOne(shop_id);
    }

    public List<Shop> getAllShops(){
        List<Shop> shops;
        return repo.findAll();
    }

    public Shop updateShop(Integer shop_id, Integer newShopChain_id, String newContacts){
        Shop shop = repo.getOne(shop_id);
        ShopChain shopChain = shopChainRepo.getOne(newShopChain_id);
        shop.setShopChain(shopChain);
        shop.setContacts(newContacts);
        return repo.save(shop);
    }
    public String deleteShop(Integer shop_id){
        repo.deleteById(shop_id);
        return "success";
    }
}
