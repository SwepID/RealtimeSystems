package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.dao.ShopChainRepository;
import com.project.things.entities.ShopChain;
import com.project.things.services.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ShopChainController {

    @Autowired
    private ShopChainService shopChainService;

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "empty page";
    }

    @RequestMapping(value = "/createShopChain", method = RequestMethod.POST)
    public ShopChain createShopChain(@RequestParam(required = false, defaultValue = "123") String name,
                                  @RequestParam(required = false, defaultValue = "www.shop.com")String website){
        return  shopChainService.createShopChain(name, website);
    }
    @RequestMapping(value = "/getShopChain/{id}", method = RequestMethod.GET)
    public ShopChain getShopChainById(@PathVariable("id") Integer id) {

        return shopChainService.getShopChainById(id);
    }

    @RequestMapping(value = "/getAllShopChains", method = RequestMethod.GET)
    public List<ShopChain> getAllShopChains(){
        return shopChainService.getAllShopChains();
    }

    @RequestMapping(value = "/updateShopChain/{id}", method = RequestMethod.PUT)
    public ShopChain updateShopChainById(@PathVariable(value = "id") Integer id,
                                      @RequestParam(required = false, defaultValue = "defaultName",
                                              name = "name") String newName,
                                      @RequestParam(required = false, defaultValue = "defaultWebSite",
                                              name = "website") String newWebSite){
        return shopChainService.updateShopChainById(id, newName, newWebSite);
    }

    @RequestMapping(value = "/deleteShopChain", method = RequestMethod.DELETE)
    public void deleteShopChainById(@RequestParam(required = true) Integer id){
        shopChainService.deleteShopChainById(id);
    }

    @RequestMapping(value = "/getAllShopsAndShopChains", method = RequestMethod.GET)
    public List<String> getAllShopsAndShopChains(){
        return shopChainService.getAllShopsAndShopChains();
    }

}
