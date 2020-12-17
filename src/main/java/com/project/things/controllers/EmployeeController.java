package com.project.things.controllers;

import com.google.gson.Gson;
import com.project.things.entities.Employee;
import com.project.things.services.EmployeeService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public Employee addEmployee(@RequestParam(required = false, defaultValue = "fname") String fname,
                                @RequestParam(required = false, defaultValue = "position") String position,
                                @RequestParam(required = false, defaultValue = "salary") Integer salary,
                                @RequestParam(required = false, defaultValue = "sname") String sname,
                                @RequestParam(required = true, value = "shop_id") Integer shop_id){
        return employeeService.createEmployee(fname, sname, position, salary, shop_id);
    }
    @RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable(value = "id") Integer id){
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
    public Employee updateEmployeeById(@RequestParam Integer id, @RequestParam String newFname,
                                     @RequestParam String newPosition, @RequestParam Integer newSalary,
                                     @RequestParam String newSname, @RequestParam Integer newShopId){
        return employeeService.updateEmployee(id, newFname, newPosition,
                newSalary, newSname, newShopId);
    }
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    public void deleteEmployeeById(@RequestParam Integer id){
        employeeService.deleteEmployee(id);
    }
}
