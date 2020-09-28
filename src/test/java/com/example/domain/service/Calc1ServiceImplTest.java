package com.example.domain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Calc1ServiceImplTest {
	
	@Autowired
	Calc1Service calc1Service;
	
	@Nested
	@DisplayName("calculateのテスト")
	public class calulateTest{
		@DisplayName("確率が正しい範囲で得られる")
		@ParameterizedTest
		@CsvSource({
		    "60, 60, 60, 60",
		    "60, 60, 1, 1",
		    "60, 1, 60, 1",
		    "60, 1, 1, 1",
		    "1, 1, 1, 1"
		})
		void getSuitableProbability(int deck, int target, int draw, int want) {
			double calcResult= calc1Service.calculate(deck, target, draw, want);
			boolean actual = calcResult >= 0 && calcResult <= 100;
			assertThat(actual, is(true));
		}
		
		@Test
		@DisplayName("山札がすべて目的のカードの時に１００％となる")
		void deckAllTarget() {
			double actual = calc1Service.calculate(40, 40, 5, 5);
			double expected = 100;
			assertThat(actual, is(expected));
		}
	}
	
	@Nested
	@DisplayName("combinationのテスト")
	public class combinationTest{
		@DisplayName("正しい組み合わせの計算結果が得られる")
		@ParameterizedTest
		@CsvSource({
		    "1, 1, 1.0",
		    "2, 1, 2.0",
		    "3, 2, 3.0",
		    "4, 4, 1.0"
		})
		void getCorrectCombination(int n, int r, double expected) {
			double actual = calc1Service.combination(n, r);
			assertThat(actual, is(expected));
		}
	}
}
