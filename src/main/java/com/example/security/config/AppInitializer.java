<<<<<<< Updated upstream:src/main/java/com/example/security/config/AppInitializer.java
package com.example.security.config;
import com.example.config.AppConfig;
=======
package com.example.mvc.config;

import com.example.AppConfig;
import jakarta.servlet.ServletContext;
>>>>>>> Stashed changes:src/main/java/com/example/mvc/config/WebServletConfiguration.java
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class AppInitializer implements WebApplicationInitializer {



    @Override
<<<<<<< Updated upstream:src/main/java/com/example/security/config/AppInitializer.java
    public void onStartup(jakarta.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx
                = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);
        ctx.scan("com.example");
      //  ctx.register(AppConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                "dispatcherExample", new DispatcherServlet(ctx));
=======
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webApplicationContext
                = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebConfig.class);
        webApplicationContext.setServletContext(container);


        ServletRegistration.Dynamic servlet = container.addServlet(
                "dispatcher", new DispatcherServlet(webApplicationContext));
>>>>>>> Stashed changes:src/main/java/com/example/mvc/config/WebServletConfiguration.java
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}

