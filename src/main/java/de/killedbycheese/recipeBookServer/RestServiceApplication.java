package de.killedbycheese.recipeBookServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * SpringBootApplication adds:
 * 		Configuration - source of bean definitions and application context
 * 		EnableAutoConfiguration
 * 		ComponentScan
 * 		 
 *
 */
@SpringBootApplication
public class RestServiceApplication {

	
	/**
	 * SpringApplication.run() to launch the App
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
}
