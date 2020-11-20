package com.project.things.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class IndexController {
    @GetMapping("/index")
    public ModelAndView home(Map<String, Object> model) {
        return new ModelAndView("index", model);
    }
}
