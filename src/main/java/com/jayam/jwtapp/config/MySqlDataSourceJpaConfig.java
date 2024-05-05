package com.jayam.jwtapp.config;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"com.jayam.jwtapp.mysql.repo"},
  entityManagerFactoryRef = "mySqlEntityManagerFactory",
  transactionManagerRef = "mySqlTransactionManager"
)
public class MySqlDataSourceJpaConfig{

    @Bean
    @ConfigurationProperties(prefix = "datasource.jwtapp")
    public DataSourceProperties mySqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.jwtapp")
    public DataSource mySqlDataSource() {
        return mySqlDataSourceProperties().initializeDataSourceBuilder().build();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(
      @Qualifier("mySqlDataSource") DataSource mySqlDataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(mySqlDataSource)
          .packages("com.jayam.jwtapp.mysql")
          .build();
    }

    @Bean
    public PlatformTransactionManager mySqlTransactionManager(
      @Qualifier("mySqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }




}
