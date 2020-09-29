package com.example.app;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

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
	
	@Nested
	@DisplayName("displayResult()のテスト")
	class displayResultTest{
		MultiValueMap<String, String> formInputs = new LinkedMultiValueMap<>();
		
		@BeforeEach
		void setUp() {
			formInputs.add("target", "4");
			formInputs.add("draw", "5");
			formInputs.add("want", "1");
		}
		
		@Test
		@DisplayName("正しい入力値の場合はエラーが存在せず、calc1.htmlに遷移する")
		void transitionThenCorrectForm() throws Exception {
			formInputs.add("deck", "40");
			MvcResult result =  mockMvc.perform(post("/Calc1").params(formInputs))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("calc1")).andReturn();
			BindingResult bindingResult = (BindingResult) result.getModelAndView()
					.getModel().get("org.springframework.validation.BindingResult.calc1Form");
			assertThat(bindingResult.hasErrors(), is(false));
		}
		
		@Test
		@DisplayName("誤った入力値の場合はエラーが存在して、calc1.htmlに遷移する")
		void transitionTheIncorrectForm() throws Exception {
			formInputs.add("deck", "61");
			MvcResult result = mockMvc.perform(post("/Calc1").params(formInputs))
					.andExpect(status().isOk())
					.andExpect(forwardedUrl("calc1")).andReturn();
			BindingResult bindingResult = (BindingResult) result.getModelAndView()
					.getModel().get("org.springframework.validation.BindingResult.calc1Form");
			assertThat(bindingResult.hasErrors(), is(true));
			
		}
	}
}