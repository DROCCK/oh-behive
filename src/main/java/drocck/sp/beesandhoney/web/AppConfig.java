package drocck.sp.beesandhoney.web;

import drocck.sp.beesandhoney.business.entities.repositories.OwnerRepository;
import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import drocck.sp.beesandhoney.business.services.OwnerService;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring4.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring4.web.view.ThymeleafTilesView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

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
/*
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix(VIEWS);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public UrlTemplateResolver urlTemplateResolver() {
        return new UrlTemplateResolver();
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
        templateEngine.addTemplateResolver(urlTemplateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addDialect(new TilesDialect());
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    *//**
     *  Handles all views except for the ones that are handled by Tiles. This view resolver
     *  will be executed as first one by Spring.
     *//*
    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver vr = new ThymeleafViewResolver();
        vr.setTemplateEngine(templateEngine());
        vr.setCharacterEncoding("UTF-8");
        vr.setOrder(Ordered.HIGHEST_PRECEDENCE);
        // all message*//* views will not be handled by this resolver as they are Tiles views
        vr.setExcludedViewNames(new String[]{"/index"});
        return vr;
    }

    *//**
     * Handles Tiles views.
     *//*
    @Bean
    public ViewResolver tilesViewResolver() {
        ThymeleafViewResolver vr = new ThymeleafViewResolver();
        vr.setTemplateEngine(templateEngine());
        vr.setViewClass(ThymeleafTilesView.class);
        vr.setCharacterEncoding("UTF-8");
        vr.setOrder(Ordered.LOWEST_PRECEDENCE);
        return vr;
    }

    @Bean
    public ThymeleafTilesConfigurer tilesConfigurer() {
        ThymeleafTilesConfigurer ttc = new ThymeleafTilesConfigurer();
        ttc.setDefinitions("/tiles-config.xml");
        return ttc;
    }*/
}
