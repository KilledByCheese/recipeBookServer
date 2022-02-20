package de.killedbycheese.recipeBookServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class RecipeBookServerApplication {

	public static void main(String[] args) {
		String pwhash = BCrypt.hashpw("password", BCrypt.gensalt(12));
		System.out.println(pwhash);
		SpringApplication.run(RecipeBookServerApplication.class, args);
	}

}
