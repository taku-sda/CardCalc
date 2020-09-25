package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.service.Calc1Service;

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
	
	private final Calc1Service calc1Service;
	
	@Autowired
	public Calc1Controller(Calc1Service calc1Service) {
		this.calc1Service = calc1Service;
	}
	
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
	
	@PostMapping
	public String displayResult(@Validated Calc1Form form, BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			return form(model);
		}
		double calcResult = calc1Service.calculate(form.getDeck(), form.getTarget(),
				form.getDraw(), form.getWant());
		//求めた確率は小数点以下2桁になるように四捨五入して格納
		model.addAttribute("calcResult", String.format("%.2f", calcResult));
		model.addAttribute("title", "CardCalc | Calc1");
		return"calc1";
	}
}
