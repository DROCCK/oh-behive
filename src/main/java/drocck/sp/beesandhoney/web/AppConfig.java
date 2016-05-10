package drocck.sp.beesandhoney.web;

import drocck.sp.beesandhoney.business.entities.validators.UserCreateFormValidator;
import drocck.sp.beesandhoney.business.services.*;
import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;

/**
 * @author Connor Elison
 * @author Robert Wilk
 * Created on 9/26/2015.
 */
@Configuration
@EnableJpaRepositories("drocck.sp.beesandhoney.business.entities.repositories")
@EntityScan("drocck.sp.beesandhoney.business.entities")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
        servletRegistrationBean.addUrlMappings("/console/*");
        return servletRegistrationBean;
    }

    @Bean
    public ViewResolver myViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =
          new InternalResourceViewResolver();
        internalResourceViewResolver.setSuffix(".html");
        // .. further setup
        return internalResourceViewResolver;
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
        HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
        factory.setEntityManagerFactory(emf);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {

        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    public PolliInspectionService polliInspectionService() {
        return new PolliInspectionService();
    }

    @Bean
    public UserCreateFormValidator userCreateFormValidator() {
        return new UserCreateFormValidator();
    }

    @Bean
    public YardService yardService() {
        return new YardService();
    }

    @Bean
    public OwnerService ownerService(){return new OwnerService();}

    @Bean
    public PersonService personService() { return new PersonService(); }

    @Bean
    public EmployeeService employeeService() { return new EmployeeService(); }

    @Bean
    public ContactInfoService contactInfoService() { return new ContactInfoService(); }

    @Bean
    public AddressService addressService() { return new AddressService(); }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public InspectionService inspectionService() {return new InspectionService(); }

    @Bean
    public CurrentUserDetailsService currentUserDetailsService() {
        return new CurrentUserDetailsService();
    }

    @Bean
    public CurrentUserService currentUserService() {
        return new CurrentUserService();
    }

    @Bean
    public RegionService regionService() { return new RegionService(); }

    @Bean
    public RoleService roleService() { return new RoleService(); }

    @Bean
    public CompanyService companyService() { return new CompanyService(); }

    @Bean
    public LocationService locationService() { return new LocationService(); }

    @Bean
    public ShipmentService shipmentService() { return new ShipmentService(); }

    @Bean
    public ContractService contractService() {
        return new ContractService();
    }

    @Bean
    public OrchardService orchardService() {
        return new OrchardService();
    }

    @Bean
    public PolliShipmentService polliShipmentService() {
        return new PolliShipmentService();
    }

    //Language beans
    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /* Not used at the moment
    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        Locale locale = new Locale("en");
        localeResolver.setDefaultLocale(locale);
        return localeResolver;
    }
    */

    @Bean
    public RequestMappingHandlerMapping handlerMapping(){
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        Object[] interceptors = new Object[1];
        interceptors[0] = localeChangeInterceptor();
        handlerMapping.setInterceptors(interceptors);
        return handlerMapping;
    }

    @Bean
    public DataInitService dataInitService() {
        return new DataInitService();
    }
}
