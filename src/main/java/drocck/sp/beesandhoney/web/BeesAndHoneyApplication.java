package drocck.sp.beesandhoney.web;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.spring4.SpringTemplateEngine;

@SpringBootApplication
public class BeesAndHoneyApplication {

    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        //...
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    public static void main(String[] args) {

        SpringApplication application =
          new SpringApplication(BeesAndHoneyApplication.class);
        SpringApplication.run(BeesAndHoneyApplication.class, args);
    }
}
