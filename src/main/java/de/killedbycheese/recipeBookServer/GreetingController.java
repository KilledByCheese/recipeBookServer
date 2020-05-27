package de.killedbycheese.recipeBookServer;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * RestController marks the class as a Controller where every method returns a domain object instead of a view
 *
 */
@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	
	/*
	 * GetMapping maps Get request to /greeting to this Method
	 * RequestParam binds the Parameter name to the name Parameter of the Method, 
	 * 				if the Parameter is not present use the Default
	 */
	@GetMapping("/greeting") 
	public Greeting greeting(@RequestParam(value ="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	

}
