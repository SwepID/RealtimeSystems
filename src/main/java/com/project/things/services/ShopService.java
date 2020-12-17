package com.project.things.services;

import com.project.things.dao.ShopChainRepository;
import com.project.things.dao.ShopRepository;
import com.project.things.entities.Shop;
import com.project.things.entities.ShopChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
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

    public Shop createShop(Integer shopChain_id, String contacts){
        Shop shop = new Shop();
        ShopChain shopChain = shopChainRepo.findById(shopChain_id).get();
        shop.setShopChain(shopChain);
        shop.setContacts(contacts);
        repo.save(shop);
        return shop;
    }

    public Shop getShopById(Integer shop_id){
        return repo.findById(shop_id).get();
    }

    public List<Shop> getAllShops(){
        return repo.findAll();
    }

    public Shop updateShop(Integer shop_id, Integer newShopChain_id, String newContacts){
        Shop shop = repo.findById(shop_id).get();
        ShopChain shopChain = shopChainRepo.findById(shop_id).get();
        shop.setShopChain(shopChain);
        shop.setContacts(newContacts);
        return repo.save(shop);
    }
    public void deleteShop(Integer shop_id){
        repo.deleteById(shop_id);
    }
}
