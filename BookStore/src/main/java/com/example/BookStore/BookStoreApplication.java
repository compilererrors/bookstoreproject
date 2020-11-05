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

*Must-have features*
*SHOPCART PONTUS+
*LOGIN ÅSA
*CSS/STYLING
*

- Login for admin
1. add/remove/edit books *USER AUTH*

- Login for user
1. Add to cart, show specific shop cart for user. *CART*
2. Add to cart (if more than 2, show on same "row". *CART*
3. Remove from cart *CART*

- Create user account
1. Save user and auth on user db *USER AUTH*

-Logistics/shopping service
1. Add items to shopcart
2. Shopcart remake (add to session for specific user)

- Styling
1. Nice navbar/header
2. Nice pagination
3. Maybe rework the list

- Controllers
1. Remove from cart
2. User /login change?
3. Rework how the shop cart shows

Nice-to-have features.
1. Lagersaldo?
2. Buy button
3. Add to orders
4. Counter at the shopcart link that adds +1 for each item
5. User "service"

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

