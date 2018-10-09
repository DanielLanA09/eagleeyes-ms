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
@EnableJpaRepositories(basePackages = "com.eagleshing.sm.scrapy.repository", entityManagerFactoryRef = "scrapyEntityManagerFactory", transactionManagerRef = "scrapyTransactionManager")
public class ScrapyDBConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix="datasource.scrapy")
	public DataSourceProperties scrapyDbProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource scrapyDataSource() {
		DataSourceProperties scrapyDataSourceProperties = scrapyDbProperties();
		return DataSourceBuilder.create()
				.driverClassName(scrapyDataSourceProperties.getDriverClassName())
				.url(scrapyDataSourceProperties.getUrl())
				.username(scrapyDataSourceProperties.getUsername())
				.password(scrapyDataSourceProperties.getPassword())
				.build();
	}
	
	@Bean
	public PlatformTransactionManager scrapyTransactionManager() {
		EntityManagerFactory factory = scrapyEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
		
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean scrapyEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(scrapyDataSource());
		factory.setPackagesToScan("com.eagleshing.sm.scrapy.model");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("hibernate.show-sql"));
		factory.setJpaProperties(jpaProperties);
		
		return factory;
	}
}
