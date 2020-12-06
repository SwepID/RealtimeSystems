package com.project.things.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/")
    public String home(Map<String, Object> model) {
        JsonObject object = new JsonObject();
        object.addProperty("property", "value");
        return object.toString();
    }
}
