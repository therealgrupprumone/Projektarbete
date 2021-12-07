package se.iths.projektarbete.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // Temp files for testing
        registry.addViewController("/").setViewName("tempHome");
        registry.addViewController("/login").setViewName("tempLogin");
        registry.addViewController("/admin").setViewName("tempAdmin");
        registry.addViewController("/chat").setViewName("tempChat");
        
    }
}
