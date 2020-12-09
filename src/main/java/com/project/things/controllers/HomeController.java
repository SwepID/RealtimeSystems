package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.services.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopChainService shopChainService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private  EmployeeService employeeService;

    @Autowired
    private CategoryService categoryService;

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

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestParam(required = false, defaultValue = "fname") String fname,
                              @RequestParam(required = false, defaultValue = "position") String position,
                              @RequestParam(required = false, defaultValue = "salary") Integer salary,
                              @RequestParam(required = false, defaultValue = "sname") String sname,
                              @RequestParam(required = true, value = "shop_id") Integer shop_id){
        return gson.toJson(Hibernate.unproxy(employeeService.createEmployee(fname, sname, position, salary, shop_id)));
    }
    @RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable(value = "id") Integer id){
        return gson.toJson(Hibernate.unproxy(employeeService.getEmployeeById(id)));
    }

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public String getAllEmployees(){
        return gson.toJson(Hibernate.unproxy(employeeService.getAllEmployees()));
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
    public String updateEmployeeById(@RequestParam Integer id, @RequestParam String newFname,
                                     @RequestParam String newPosition, @RequestParam Integer newSalary,
                                     @RequestParam String newSname, @RequestParam Integer newShopId){
        return gson.toJson(Hibernate.unproxy(employeeService.updateEmployee(id, newFname, newPosition,
                newSalary, newSname, newShopId)));
    }
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    public String deleteEmployeeById(@RequestParam Integer id){
        return gson.toJson(Hibernate.unproxy(employeeService.deleteEmployee(id)));
    }
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(@RequestParam(required = false, defaultValue = "category_name") String name,
                                 @RequestParam Integer shop_id){
        return gson.toJson(Hibernate.unproxy(categoryService.createCategory(shop_id, name)));
    }

    @RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
    public String getCategoryById(@PathVariable(value = "id") Integer id){
        return gson.toJson(Hibernate.unproxy(categoryService.getCategory(id)));
    }
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public String getAllCategories(){
        return gson.toJson(Hibernate.unproxy(categoryService.getAllCategories()));
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
    public String updateCategoryById(@RequestParam Integer id, @RequestParam Integer newShopId,
                                     @RequestParam(required = false, defaultValue = "newName")
                                             String newName){
        return gson.toJson(Hibernate.unproxy(categoryService.updateCategory(id, newShopId, newName)));
    }
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
    public String deleteCategory(@RequestParam Integer id){
        return gson.toJson(Hibernate.unproxy(categoryService.deleteCategory(id)));
    }

    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public String createItem(@RequestParam(required = false, defaultValue = "some description")
                                         String description, @RequestParam(required = false,
            defaultValue = "some name") String name, @RequestParam Integer category_id){
        return gson.toJson(Hibernate.unproxy(itemService.createItem(name, description, category_id)));
    }

    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    public String getItemById(@PathVariable(value = "id") Integer id){
        return gson.toJson(Hibernate.unproxy(itemService.getItemById(id)));
    }
    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public String getAllItems(){
        return gson.toJson(Hibernate.unproxy(itemService.getAllItems()));
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public String updateItem(@RequestParam Integer id, @RequestParam String newDescription, @RequestParam String newName, @RequestParam Integer newCategoryId){
        return gson.toJson(Hibernate.unproxy(itemService.updateItem(id,newName, newDescription, newCategoryId)));
    }
    @RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE)
    public String deleteItemById(@RequestParam Integer id){
        return gson.toJson(Hibernate.unproxy(itemService.deleteItem(id)));
    }

    @RequestMapping(value = "/getAllShopsAndShopChains", method = RequestMethod.GET)
    public String getAllShopsAndShopChains(){
        return gson.toJson(Hibernate.unproxy(shopChainService.getAllShopsAndShopChains()));
    }

}
