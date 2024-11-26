
package com.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class GreetController {

    public GreetController(){
        log.info("GreetController class has created as a Controller");
    }
    private static final Logger log = LoggerFactory.getLogger(GreetController.class);

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name, ModelMap model, HttpServletRequest request) {
        String greet = "Hello !!! " + name + ", How are you?";
        model.addAttribute("greet", greet);
       log.info(greet);

        HttpSession session = request.getSession(false); // Do not create a new session
        if (session != null) {
            System.out.println("Session ID: " + session.getId());
            // You can also access the SecurityContext from the session
            SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
            if (securityContext != null) {
                log.info("Authenticated User: " + securityContext.getAuthentication().getName());
            }
        } else {
            log.info("No session exists.");
        }

        return "greet"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }
    @GetMapping("/greet")
    public String Defaultgreet(ModelMap model) {
        String greet = "Hello !!! , How are you?";
        model.addAttribute("greet", greet);
        log.info(greet);
        return "greet"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }
    @GetMapping("/public")
    public String publicPage( ModelMap model) {
        String greet = "Hello !!! , How are you?";
        model.addAttribute("greet", greet);
        System.out.println(greet);

        return "public"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }

    @GetMapping("/admin")
    public String adminPage( ModelMap model) {
        String greet = "Hello !!! , How are you?";
        model.addAttribute("greet", greet);
        System.out.println(greet);

        return "admin"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }

    @GetMapping("/user")
    public String userPage( ModelMap model) {
        String greet = "Hello !!! , How are you?";
        model.addAttribute("greet", greet);
        System.out.println(greet);

        return "user"; // Returns the logical view name "greet" (mapped to a JSP or other view)
    }
}
