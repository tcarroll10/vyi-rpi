package com.tcarroll10.vyi.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Java custom configuration for multiple SQL, JDBC data-sources, following
 * spring boot user guide suggestion, with capability to extract the passwords
 * from a secrets file. Excepting the password, all other db-connection
 * attributes (i.e. username, host, port, databasename) are always read from the
 * spring boot properties mechanism (application.properties, environment
 * variables, system or spring command-line arguments, etc).
 * 
 * <li>The naming of the properties will be specific for each data-source.
 * Below, an example of all properties available for a DB2 "ups" datasource. If
 * the <code>app.datasource.ups.secrets.enabled</code> property is true, the
 * <code>secrets.location</code> property is required and the
 * <code>app.datasource.ups.password</code> property is not required.
 * 
 *
 * @author Jorge Donado [f243901]
 * @since 12/3/2018 8:05 AM
 *
 */
@Configuration
public class MultiSqlDbConfig {

	@Primary
	@Bean
	@ConfigurationProperties("app.datasource.vyi")
	public DataSourceProperties vyiDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "vyi-db")
	@ConfigurationProperties("app.datasource.vyi.configuration")
	public HikariDataSource vyiDataSource() {
		return vyiDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();

	}

}
