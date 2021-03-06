package com.example.app;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class WelcomeControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WelcomeController target;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@Test
	@DisplayName("indexに正しく遷移する")
	void testIndex() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
			.andExpect(forwardedUrl("index"));
	}

	@Test
	@DisplayName("modelに正しいデータが挿入されている")
	void testModel() throws Exception {
		mockMvc.perform(get("/")).andExpect(model()
				.attribute("title", is("CardCalc")));
	}

}

