
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetController {

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name, ModelMap model) {
        String greet = "Hello !!! " + name + ", How are you?";
        model.addAttribute("greet", greet);
        System.out.println(greet);

        return "greet"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }
}
