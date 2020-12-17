package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.entities.Shop;
import com.project.things.services.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    Gson gson = new Gson();

    @RequestMapping(value = "/createShop", method = RequestMethod.POST)
    public Shop createShop(@RequestParam(required = true) Integer shopChain_id,
                           @RequestParam(required = true) String contacts){
        return shopService.createShop(shopChain_id, contacts);
    }

    @RequestMapping(value = "/getAllShops", method = RequestMethod.GET)
    public List<Shop> getAllShops(){
        return shopService.getAllShops();
    }
    @RequestMapping(value = "/getShop/{id}", method = RequestMethod.GET)
    public Shop getShopById(@PathVariable(value = "id") Integer id){
        return shopService.getShopById(id);
    }
    @RequestMapping(value = "/updateShop", method = RequestMethod.PUT)
    public Shop updateShopById(@RequestParam Integer id, @RequestParam Integer newShopChainId, @RequestParam(required = false, defaultValue = "Contacts") String newContacts){
        return shopService.updateShop(id, newShopChainId, newContacts);
    }

    @RequestMapping(value = "/deleteShop", method = RequestMethod.DELETE)
    public void deleteShopById(Integer id){
        shopService.deleteShop(id);
    }
}
