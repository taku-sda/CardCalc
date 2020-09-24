package com.example.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class Calc1ControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	Calc1Controller target;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}
	
	@Test
	@DisplayName("calc1に正しく遷移する")
	void testCalc1Transition() throws Exception {
		mockMvc.perform(get("/Calc1")).andExpect(status().isOk())
			.andExpect(forwardedUrl("calc1"));
	}

}
