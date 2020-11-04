package com.example.BookStore;

/*

Requirements:

- Create a Spring Boot MVC application with Thymeleaf as the View technology.

- Use CSS to create a nice looking user interface with HTML and CSS.

- Create at least one Unit Test that successfully tests some method in the application.

- Use Git and GitHub and send a link to the GitHub repo to the teacher.

- Send a Project description and your cool team name to the teacher. The Project description should include:

Short description of the project
- Webshop for books with a shop cart, user account page etc.
Must-have features
-
Nice-to-have features.

 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}

