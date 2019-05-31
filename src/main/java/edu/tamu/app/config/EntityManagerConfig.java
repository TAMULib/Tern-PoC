package edu.tamu.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
// @EnableTransactionManagement
public class EntityManagerConfig {
    @Value("${primary.datasource.url}")
    private String PRIMARY_URL;

    @Value("${primary.datasource.driverClassName}")
    private String PRIMARY_DRIVERCLASSNAME;

    @Value("${primary.jpa.database-platform}")
    private String PRIMARY_DATABASE_PLATFORM;

    @Value("${primary.datasource.username}")
    private String PRIMARY_USERNAME;

    @Value("${primary.datasource.password}")
    private String PRIMARY_PASSWORD;

    @Value("${primary.datasource.validation-query}")
    private String PRIMARY_VALIDATION_QUERY;

    @Value("${primary.jpa.hibernate.ddl-auto}")
    private String PRIMARY_HIBERNATE_DDLAUTO;

    @Value("${extraction.datasource.url}")
    private String EXTRACTION_URL;

    @Value("${extraction.datasource.driverClassName}")
    private String EXTRACTION_DRIVERCLASSNAME;

    @Value("${extraction.jpa.database-platform}")
    private String EXTRACTION_DATABASE_PLATFORM;

    @Value("${extraction.datasource.username}")
    private String EXTRACTION_USERNAME;

    @Value("${extraction.datasource.password}")
    private String EXTRACTION_PASSWORD;

    @Value("${extraction.datasource.validation-query}")
    private String EXTRACTION_VALIDATION_QUERY;

    @Value("${extraction.jpa.hibernate.ddl-auto}")
    private String EXTRACTION_HIBERNATE_DDLAUTO;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(primaryDataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] { "edu.tamu.app.model", "edu.tamu.weaver.wro.model" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPersistenceUnitName("primary-database");
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", PRIMARY_HIBERNATE_DDLAUTO);
        properties.setProperty("hibernate.dialect", PRIMARY_DATABASE_PLATFORM);
        entityManagerFactoryBean.setJpaProperties(properties);
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean;
    }

    private DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PRIMARY_DRIVERCLASSNAME);
        dataSource.setUrl(PRIMARY_URL);
        dataSource.setUsername(PRIMARY_USERNAME);
        dataSource.setPassword(PRIMARY_PASSWORD);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean extractionEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(extractionDataSource());
        // em.setDataSource(primaryDataSource());
        em.setPackagesToScan(new String[] {});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("extractor-database");
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", EXTRACTION_HIBERNATE_DDLAUTO);
        properties.setProperty("hibernate.dialect", EXTRACTION_DATABASE_PLATFORM);
        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em;
    }

    private DataSource extractionDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(EXTRACTION_DRIVERCLASSNAME);
        dataSource.setUrl(EXTRACTION_URL);
        dataSource.setUsername(EXTRACTION_USERNAME);
        dataSource.setPassword(EXTRACTION_PASSWORD);
        return dataSource;
    }

}