package com.project.things.controllers;

import com.project.things.entities.Shop;
import com.project.things.entities.ShopChain;
import com.project.things.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "empty page";
    }

    @RequestMapping(value = "/createShopChain", method = RequestMethod.POST)
    public String createShopChain(@RequestParam(required = false, defaultValue = "123") String name,
                                  @RequestParam(required = false, defaultValue = "www.shop.com")String website){
        shopChainService.createShopChain(name, website);
        return name;
        //return shopService.createShop(name);
    }
    @RequestMapping(value = "/getShopChain/{id}", method = RequestMethod.GET)
    public String getShopChainById(@PathVariable("id") Integer id){
        return shopChainService.getShopChainById(id).toString();
    }

    @RequestMapping(value = "/getAllShopChains", method = RequestMethod.GET)
    public String getAllShopChains(){
        List<ShopChain> shopChains = shopChainService.getAllShopChains();
        StringBuilder stringBuilder = new StringBuilder();
        shopChains.forEach(elem-> {
            stringBuilder.append(elem.toString() + "\n");
        });
        return stringBuilder.toString();
    }

    @RequestMapping(value = "/updateShopChain/{id}", method = RequestMethod.PUT)
    public String updateShopChainById(@PathVariable(value = "id") Integer id,
                                      @RequestParam(required = false, defaultValue = "defaultName",
                                              name = "name") String newName,
                                      @RequestParam(required = false, defaultValue = "defaultWebSite",
                                              name = "website") String newWebSite){
        return shopChainService.updateShopChainById(id, newName, newWebSite).toString();
    }

    @RequestMapping(value = "/deleteShopChain", method = RequestMethod.DELETE)
    public String deleteShopChainById(@RequestParam(required = true) Integer id){
        return shopChainService.deleteShopChainById(id);
    }

    @RequestMapping(value = "/createShop", method = RequestMethod.POST)
    public String createShop(@RequestParam(required = true) Integer shopChain_id,
                             @RequestParam(required = true) String contacts){
        return shopService.createShop(shopChain_id, contacts).toString();
    }

    @RequestMapping(value = "/getAllShops", method = RequestMethod.GET)
    public String getAllShops(){
        List<Shop> shops = shopService.getAllShops();
        StringBuilder builder = new StringBuilder();
        shops.forEach(elem -> builder.append(elem + "\n"));
        return builder.toString();
    }
    @RequestMapping(value = "/getShop/{id}", method = RequestMethod.GET)
    public String getShopById(@PathVariable(value = "id") Integer id){
        return shopService.getShopById(id).toString();
    }
    @RequestMapping(value = "/updateShop", method = RequestMethod.PUT)
    public String updateShopById(@RequestParam Integer id, @RequestParam Integer newShopChainId, @RequestParam(required = false, defaultValue = "Contacts") String newContacts){
        return shopService.updateShop(id, newShopChainId, newContacts).toString();
    }

    @RequestMapping(value = "/deleteShop", method = RequestMethod.DELETE)
    public String deleteShopById(Integer id){
        return shopService.deleteShop(id);
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestParam(required = false, defaultValue = "fname") String fname,
                              @RequestParam(required = false, defaultValue = "position") String position,
                              @RequestParam(required = false, defaultValue = "salary") Integer salary,
                              @RequestParam(required = false, defaultValue = "sname") String sname,
                              @RequestParam(required = true, value = "shop_id") Integer shop_id){
        return employeeService.createEmployee(fname, sname, position, salary, shop_id).toString();
    }
    @RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable(value = "id") Integer id){
        return employeeService.getEmployeeById(id).toString();
    }

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public String getAllEmployees(){
        StringBuilder builder = new StringBuilder();
        employeeService.getAllEmployees().forEach(elem -> builder.append(elem + "\n"));
        return builder.toString();
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
    public String updateEmployeeById(@RequestParam Integer id, @RequestParam String newFname,
                                     @RequestParam String newPosition, @RequestParam Integer newSalary,
                                     @RequestParam String newSname, @RequestParam Integer newShopId){
        return employeeService.updateEmployee(id, newFname, newPosition, newSalary, newSname, newShopId)
                .toString();
    }
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    public String deleteEmployeeById(@RequestParam Integer id){
        return employeeService.deleteEmployee(id);
    }
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(@RequestParam(required = false, defaultValue = "category_name") String name,
                                 @RequestParam Integer shop_id){
        return categoryService.createCategory(shop_id, name).toString();
    }

    @RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
    public String getCategoryById(@PathVariable(value = "id") Integer id){
        return categoryService.getCategory(id).toString();
    }
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public String getAllCategories(){
        StringBuilder builder = new StringBuilder();
        categoryService.getAllCategories().forEach(elem -> builder.append(elem + "\n"));
        return builder.toString();
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT)
    public String updateCategoryById(@RequestParam Integer id, @RequestParam Integer newShopId,
                                     @RequestParam(required = false, defaultValue = "newName")
                                             String newName){
        return categoryService.updateCategory(id, newShopId, newName).toString();
    }
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
    public String deleteCategory(@RequestParam Integer id){
        return categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public String createItem(@RequestParam(required = false, defaultValue = "some description")
                                         String description, @RequestParam(required = false,
            defaultValue = "some name") String name, @RequestParam Integer category_id){
        return itemService.createItem(name, description, category_id).toString();
    }

    @RequestMapping(value = "/getItem/{id}", method = RequestMethod.GET)
    public String getItemById(@PathVariable(value = "id") Integer id){
        return itemService.getItemById(id).toString();
    }
    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public String getAllItems(){
        StringBuilder builder = new StringBuilder();
        itemService.getAllItems().forEach(elem -> builder.append(elem + "\n"));
        return builder.toString();
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public String updateItem(@RequestParam Integer id, @RequestParam String newDescription, @RequestParam String newName, @RequestParam Integer newCategoryId){
        return itemService.updateItem(id,newName, newDescription, newCategoryId).toString();
    }
    @RequestMapping(value = "/deleteItem", method = RequestMethod.DELETE)
    public String deleteItemById(@RequestParam Integer id){
        return itemService.deleteItem(id);
    }

}
