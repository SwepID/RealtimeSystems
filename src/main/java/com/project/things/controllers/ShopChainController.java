package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.services.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShopChainController {

    @Autowired
    private ShopChainService shopChainService;

    Gson gson = new Gson();

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "empty page";
    }

    @RequestMapping(value = "/createShopChain", method = RequestMethod.POST)
    public String createShopChain(@RequestParam(required = false, defaultValue = "123") String name,
                                  @RequestParam(required = false, defaultValue = "www.shop.com")String website){
        return  gson.toJson(Hibernate.unproxy(shopChainService.createShopChain(name, website)));
    }
    @RequestMapping(value = "/getShopChain/{id}", method = RequestMethod.GET)
    public String getShopChainById(@PathVariable("id") Integer id) {
        return gson.toJson(Hibernate.unproxy(shopChainService.getShopChainById(id)));
    }

    @RequestMapping(value = "/getAllShopChains", method = RequestMethod.GET)
    public String getAllShopChains(){
        return gson.toJson(Hibernate.unproxy(shopChainService.getAllShopChains()));
    }

    @RequestMapping(value = "/updateShopChain/{id}", method = RequestMethod.PUT)
    public String updateShopChainById(@PathVariable(value = "id") Integer id,
                                      @RequestParam(required = false, defaultValue = "defaultName",
                                              name = "name") String newName,
                                      @RequestParam(required = false, defaultValue = "defaultWebSite",
                                              name = "website") String newWebSite){
        return gson.toJson(Hibernate.unproxy(shopChainService.updateShopChainById(id, newName, newWebSite)));
    }

    @RequestMapping(value = "/deleteShopChain", method = RequestMethod.DELETE)
    public String deleteShopChainById(@RequestParam(required = true) Integer id){
        return gson.toJson(Hibernate.unproxy(shopChainService.deleteShopChainById(id)));
    }

    @RequestMapping(value = "/getAllShopsAndShopChains", method = RequestMethod.GET)
    public String getAllShopsAndShopChains(){
        return gson.toJson(Hibernate.unproxy(shopChainService.getAllShopsAndShopChains()));
    }

}
