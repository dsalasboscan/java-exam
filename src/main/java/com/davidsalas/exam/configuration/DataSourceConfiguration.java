package com.davidsalas.exam.configuration;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

  private static final String PACKAGE_TO_SCAN = "com.davidsalas.exam";
  private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
  private static final String DATABASE_CONNECTION_STRING = "jdbc:h2:mem:db";
  private static final String DATABASE_USERNAME = "sa";
  private static final String DATABASE_PASSWORD = "sa";

  @PostConstruct
  public void setUpDatabases() {
    DatabasePopulatorUtils.execute(this.createDatabasePopulator(new String[]{"sql-scripts/db-ddl.sql"}), dataSource());
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(DRIVER_CLASS_NAME);
    dataSource.setUrl(DATABASE_CONNECTION_STRING);
    dataSource.setUsername(DATABASE_USERNAME);
    dataSource.setPassword(DATABASE_PASSWORD);

    return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory.getObject());

    return transactionManager;
  }

  private Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

    return hibernateProperties;
  }

  private ResourceDatabasePopulator createDatabasePopulator(final String[] files) {
    final ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();

    for (final String file : files) {
      resourceDatabasePopulator.addScript(new ClassPathResource(file));
    }

    return resourceDatabasePopulator;
  }
}