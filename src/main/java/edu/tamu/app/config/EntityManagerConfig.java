package edu.tamu.app.config;

import java.util.Properties;

import javax.sql.DataSource;

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
    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    entityManagerFactoryBean.setJpaProperties(properties);
    entityManagerFactoryBean.afterPropertiesSet();
    return entityManagerFactoryBean;
  }
    
  private DataSource primaryDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    dataSource.setUsername( "spring" );
    dataSource.setPassword( "spring" );
    return dataSource;
  }
    
  @Bean
  public LocalContainerEntityManagerFactoryBean extractionEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(extractionDataSource());
    //em.setDataSource(primaryDataSource());
    em.setPackagesToScan(new String[] {});
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setPersistenceUnitName("extractor-database");
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "none");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
    em.setJpaProperties(properties);
    em.afterPropertiesSet();
    return em;
  }
    
  private DataSource extractionDataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
    dataSource.setUrl("jdbc:oracle:thin:@[hostname]:1521:[dbname]");
    dataSource.setUsername( "[username]" );
    dataSource.setPassword( "[Password]" );
    return dataSource;
  }

}