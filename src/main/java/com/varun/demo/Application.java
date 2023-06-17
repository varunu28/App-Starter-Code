package com.varun.demo;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.password}")
	private String datasourcePassword;

	@Value("${spring.datasource.username}")
	private String datasourceUsername;

	private static final String POSTGRES_DRIVER_CLASS = "org.postgresql.Driver";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public DataSource postgresDataSource() {
		return DataSourceBuilder.create()
				.url(datasourceUrl)
				.password(datasourcePassword)
				.username(datasourceUsername)
				.driverClassName(POSTGRES_DRIVER_CLASS)
				.build();
	}

	@Bean
	public StorageProvider storageProvider(JobMapper jobMapper, DataSource dataSource) {
		StorageProvider storageProvider = new PostgresStorageProvider(dataSource);
		storageProvider.setJobMapper(jobMapper);
		return storageProvider;
	}
}
