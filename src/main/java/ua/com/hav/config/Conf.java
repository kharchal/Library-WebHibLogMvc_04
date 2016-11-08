package ua.com.hav.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ua.com.hav.dao.Holder;
import ua.com.hav.dao.KindDao;
import ua.com.hav.domain.Kind;
import ua.com.hav.domain.Pet;
import ua.com.hav.service.KindService;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Created by Юля on 12.10.2016.
 */

@Configuration
@EnableWebMvc
//@EnableJpaRepositories
@ComponentScan("ua.com.hav")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class Conf extends WebMvcConfigurerAdapter {
    private Logger logger = Logger.getLogger(Conf.class);

    @Autowired
    private Environment env;
    @Bean
    public HibernateTemplate template() {
        return new HibernateTemplate(sessionFactory());
    }
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(getDataSource());
        lsfb.setPackagesToScan("ua.com.hav");
        lsfb.setHibernateProperties(hibernateProperties());
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lsfb.getObject();
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driver"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.root"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager hibTransMan(){
        return new HibernateTransactionManager(sessionFactory());
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return properties;
    }


    @Bean
    public ViewResolver viewResolver() {
        logger.info("view resolver.....");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/WEB-INF/messages");

        source.setDefaultEncoding("utf-8");
        return source;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));

        return resolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }


    @Bean
    public Holder<Pet> petBase() {
        logger.info("An instance of base is on hand");
        return new Holder(Pet.class);
    }

    @Bean
    public Holder<Kind> kindBase() {
        return new Holder<>(Kind.class);
    }

    @Bean
    public KindDao kindDao() {
        return new KindDao();
    }

    @Bean
    public KindService kindService() {
        return new KindService();
    }

    @Bean
    public String getContextUrl() {
        return "/homezoo";
    }
}
