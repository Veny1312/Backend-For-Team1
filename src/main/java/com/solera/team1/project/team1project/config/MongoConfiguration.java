package com.solera.team1.project.team1project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@EnableWebMvc
@Configuration

public class MongoConfiguration implements WebMvcConfigurer { //extends AbstractMongoClientConfiguration {
	
	    public void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping("/**")
	                .allowedMethods("GET", "POST", "PUT","PATCH");
	    }
	 
	}
	
//	@Value("${port}")
//	private String port;
//	
//	@Value("${dbname}")
//	private String dbname;
//	
//	@Override
//	protected String getDatabaseName() {	
//		return dbname;
//	}
//
//	@Override
//	public MongoClient mongoClient() {
//		return MongoClients.create();
//	}
//	
//	@Override
//	public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter) {
//		return new MongoTemplate(mongoClient(), getDatabaseName());
//	}

