package com.example.app;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
class Calc1FormTest {	
	 Calc1Form form = new Calc1Form();
	 BindingResult bindingResult = new BindException(form, "Calc1Form");
	 
	@Autowired
	Validator validator;
	
	@BeforeEach
	void setUp() {
		form.setDeck(40);
		form.setTarget(4);
		form.setDraw(5);
		form.setWant(1);
	}
	
	@DisplayName("deckのバリデーションが正しい")
	@ParameterizedTest
	@CsvSource({
	    "61, '山札は60枚以下です'",
	    "0, '1枚以上にしてください'"
	})
	void deckValidation(int deckNum, String message) {
		form.setDeck(deckNum);
		validator.validate(form, bindingResult);
		assertThat(bindingResult.hasFieldErrors("deck"), is(true));
		assertThat(bindingResult.getFieldErrors("deck").stream()
        		.anyMatch(it -> it.getDefaultMessage().equals(message) ), is(true));
	}
	
	@DisplayName("targetのバリデーションが正しい")
	@ParameterizedTest
	@CsvSource({
	    "41,  '目的のカードの枚数は山札以下にしてください'",
	    "0,  '1枚以上にしてください'"
	})
	void targetValidation(int targetNum, String message) {
		form.setTarget(targetNum);
		validator.validate(form, bindingResult);
		assertThat(bindingResult.hasFieldErrors("target"), is(true));
		assertThat(bindingResult.getFieldErrors("target").stream()
        		.anyMatch(it -> it.getDefaultMessage().equals(message) ), is(true));
	}
	
	@DisplayName("drawのバリデーションが正しい")
	@ParameterizedTest
	@CsvSource({
	    "41,  '引く枚数は山札以下にしてください'",
	    "0,  '1枚以上にしてください'"
	})
	void drawValidation(int drawNum, String message) {
		form.setDraw(drawNum);
		validator.validate(form, bindingResult);
		assertThat(bindingResult.hasFieldErrors("draw"), is(true));
		assertThat(bindingResult.getFieldErrors("draw").stream()
        		.anyMatch(it -> it.getDefaultMessage().equals(message) ), is(true));
	}
	
	@DisplayName("wantのバリデーションが正しい")
	@ParameterizedTest
	@CsvSource({
		"5,  '引きたい枚数は目的のカードの枚数以下にしてください'",
	    "6,  '引きたい枚数は引く枚数以下にしてください'",
	    "0,  '1枚以上にしてください'"
	})
	void wantValidation(int wantNum, String message) {
		form.setWant(wantNum);
		validator.validate(form, bindingResult);
		assertThat(bindingResult.hasFieldErrors("want"), is(true));
        assertThat(bindingResult.getFieldErrors("want").stream()
        		.anyMatch(it -> it.getDefaultMessage().equals(message) ), is(true));
	}
}
