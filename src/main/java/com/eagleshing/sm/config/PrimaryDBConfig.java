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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.eagleshing.sm.repository", entityManagerFactoryRef = "primaryEntityManagerFactory", transactionManagerRef = "primaryTransactionManager")
public class PrimaryDBConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix="datasource.primary")
	public DataSourceProperties primaryDbProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	public DataSource primaryDataSource() {
		DataSourceProperties primaryDataSourceProperties = primaryDbProperties();
		return DataSourceBuilder.create()
				.driverClassName(primaryDataSourceProperties.getDriverClassName())
				.url(primaryDataSourceProperties.getUrl())
				.username(primaryDataSourceProperties.getUsername())
				.password(primaryDataSourceProperties.getPassword())
				.build();
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager primaryTransactionManager() {
		EntityManagerFactory factory = primaryEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
		
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(primaryDataSource());
		factory.setPackagesToScan("com.eagleshing.sm.model");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("hibernate.show-sql"));
		factory.setJpaProperties(jpaProperties);
		
		return factory;
	}
}
