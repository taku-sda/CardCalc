package com.example.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
	
	@Nested
	@DisplayName("form()のテスト")
	class formTest{
		@Test
		@DisplayName("calc1.htmlに正しく遷移する")
		void testCalc1Transition() throws Exception {
			mockMvc.perform(get("/Calc1")).andExpect(status().isOk())
				.andExpect(forwardedUrl("calc1"));
		}
		
		@Test
		@DisplayName("Calc1Formに初期値が正しく設定されている")
		void testCalc1FormSetUp() throws Exception {
			MvcResult result = mockMvc.perform(get("/Calc1"))
					.andReturn();
			Calc1Form resultForm = (Calc1Form) result.getModelAndView().getModel().get("calc1Form");
			
			assertThat(resultForm.getDeck(), is(40));
			assertThat(resultForm.getTarget(), is(4));
			assertThat(resultForm.getDraw(), is(5));
			assertThat(resultForm.getWant(), is(1));	
		}
	}
}
