package drocck.sp.beesandhoney.web;

import drocck.sp.beesandhoney.business.entities.repositories.OwnerRepository;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import drocck.sp.beesandhoney.business.services.OwnerService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Connor Elison
 * Created on 9/26/2015.
 */
@Configuration
public class AppConfig {

    @Bean
    public YardService yardService() {
        return new YardService();
    }

    @Bean
    public YardRepository yardRepository() {
        return new YardRepository();
    }

    @Bean
    public OwnerService ownerService(){return new OwnerService();}

    @Bean
    public OwnerRepository ownerRepository(){return new OwnerRepository();}
}
