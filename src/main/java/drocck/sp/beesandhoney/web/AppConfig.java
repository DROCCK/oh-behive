package drocck.sp.beesandhoney.web;

import drocck.sp.beesandhoney.business.entities.validators.UserCreateFormValidator;
import drocck.sp.beesandhoney.business.services.*;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("drocck.sp.beesandhoney.business.entities.repositories");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {

        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
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
    public RoleService roleService() { return new RoleService(); }

    @Bean
    public DropSiteService dropSiteService() { return new DropSiteService(); }
}
