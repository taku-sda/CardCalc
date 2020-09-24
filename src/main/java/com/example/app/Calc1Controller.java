package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Calc1")
public class Calc1Controller {
	/** 山札の初期値. */
	private static final int DEFAULT_DECK_NUMBER = 40;
	/** 目的のカード枚数の初期値. */
	private static final int  DEFAULT_TARGET_NUMBER = 4;
	/** 引く枚数の初期値. */
	private static final int  DEFAULT_DRAW_NUMBER = 5;
	/** 引きたい枚数の初期値. */
	private static final int  DEFAULT_WANT_NUMBER = 1;
	
	@ModelAttribute
	public Calc1Form setUpform() {
		Calc1Form form = new Calc1Form();
		form.setDeck(DEFAULT_DECK_NUMBER);
		form.setTarget(DEFAULT_TARGET_NUMBER);
		form.setDraw(DEFAULT_DRAW_NUMBER);
		form.setWant(DEFAULT_WANT_NUMBER);
		return form;
	}
	
	@GetMapping
	public String form(Model model) {
		model.addAttribute("title", "CardCalc | Calc1");
		return "calc1";
	}
}
