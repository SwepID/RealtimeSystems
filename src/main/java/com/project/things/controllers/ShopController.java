package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.services.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    Gson gson = new Gson();

    @RequestMapping(value = "/createShop", method = RequestMethod.POST)
    public String createShop(@RequestParam(required = true) Integer shopChain_id,
                             @RequestParam(required = true) String contacts){
        return gson.toJson(Hibernate.unproxy(shopService.createShop(shopChain_id, contacts)));
    }

    @RequestMapping(value = "/getAllShops", method = RequestMethod.GET)
    public String getAllShops(){
        return gson.toJson(Hibernate.unproxy(shopService.getAllShops()));
    }
    @RequestMapping(value = "/getShop/{id}", method = RequestMethod.GET)
    public String getShopById(@PathVariable(value = "id") Integer id){
        return gson.toJson(Hibernate.unproxy(shopService.getShopById(id)));
    }
    @RequestMapping(value = "/updateShop", method = RequestMethod.PUT)
    public String updateShopById(@RequestParam Integer id, @RequestParam Integer newShopChainId, @RequestParam(required = false, defaultValue = "Contacts") String newContacts){
        return gson.toJson(Hibernate.unproxy(shopService.updateShop(id, newShopChainId, newContacts)));
    }

    @RequestMapping(value = "/deleteShop", method = RequestMethod.DELETE)
    public String deleteShopById(Integer id){
        return gson.toJson(Hibernate.unproxy(shopService.deleteShop(id)));
    }
}
