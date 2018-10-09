package com.eagleshing.sm.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.eagleshing.sm.old.repository", entityManagerFactoryRef = "oldEntityManagerFactory", transactionManagerRef = "oldTransactionManager")
public class OldDBConfig {

	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix="datasource.old")
	public DataSourceProperties oldDbProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource oldDataSource() {
		DataSourceProperties oldDataSourceProperties = oldDbProperties();
		return DataSourceBuilder.create()
				.driverClassName(oldDataSourceProperties.getDriverClassName())
				.url(oldDataSourceProperties.getUrl())
				.username(oldDataSourceProperties.getUsername())
				.password(oldDataSourceProperties.getPassword())
				.build();
	}
	
	@Bean
	public PlatformTransactionManager oldTransactionManager() {
		EntityManagerFactory factory = oldEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
		
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean oldEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(oldDataSource());
		factory.setPackagesToScan("com.eagleshing.sm.old.model");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("hibernate.show-sql"));
		factory.setJpaProperties(jpaProperties);
		
		return factory;
	}
}
