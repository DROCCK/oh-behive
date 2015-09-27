package drocck.sp.beesandhoney.web;

import drocck.sp.beesandhoney.business.entities.repositories.YardRepository;
import drocck.sp.beesandhoney.business.services.YardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring4.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring4.web.view.ThymeleafTilesView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * @author Robert Wilk
 * @author Connor Elison
 * Created on 9/26/2015.
 */
@Configuration
public class AppConfig {

    /**
     * Getting errors
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
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver vr = new ThymeleafViewResolver();
        vr.setTemplateEngine(templateEngine());
        vr.setCharacterEncoding("UTF-8");
        vr.setOrder(Ordered.HIGHEST_PRECEDENCE);
        // all message/* views will not be handled by this resolver;
        vr.setExcludedViewNames(new String[]{"message/*"});
        return vr;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        //...
        templateEngine.addDialect(new TilesDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafTilesConfigurer tilesConfigurer() {
        ThymeleafTilesConfigurer ttc = new ThymeleafTilesConfigurer();
        ttc.setDefinitions("/resources/templates/tiles-defs.xml");
        return ttc;
    }
     **/
    @Bean
    public YardService yardService() {
        return new YardService();
    }

    @Bean
    public YardRepository yardRepository() {
        return new YardRepository();
    }
}
