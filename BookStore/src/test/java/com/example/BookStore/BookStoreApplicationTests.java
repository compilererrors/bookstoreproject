package com.example.BookStore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	private CartHandler cartHandler;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetBook() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/book")
		)
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string(containsString("New book")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("Cloud-Native Applications in Java")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("Spring Boot in Action")));
	}

	@Test
	public void testAddBookToCart(){



	}

}

