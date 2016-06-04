package drocck.sp.beesandhoney.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class BeesAndHoneyApplication {

    // Match everything without a suffix (so not a static resource)
//    @RequestMapping(value = "/{path:[^\\.]*}")
//    public String redirect() {
//        // Forward to home page so that route is preserved.
//        return "forward:/";
//    }

    public static void main(String[] args) {
        new SpringApplication(BeesAndHoneyApplication.class).run(args);
    }
}
