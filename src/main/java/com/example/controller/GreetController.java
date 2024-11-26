
package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetController {

    public GreetController(){
        log.info("GreetController class has created as a Controller");
    }
    private static final Logger log = LoggerFactory.getLogger(GreetController.class);

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name, ModelMap model) {
        String greet = "Hello !!! " + name + ", How are you?";
        model.addAttribute("greet", greet);
        System.out.println(greet);

        return "greet"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }
}
