package de.killedbycheese.recipeBookServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = { "de.killedbycheese.recipeBookServer.auth.repository",
		"de.killedbycheese.recipeBookServer.category.repository",
		"de.killedbycheese.recipeBookServer.recipe.repository" })
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "testDB";
	}

	@Override
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString(
				"mongodb://dev:password@localhost:27017/?authSource=testDB");
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	@Override
	public boolean autoIndexCreation() {
		return true;
	}

}
