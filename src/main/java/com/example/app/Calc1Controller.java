package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Calc1")
public class Calc1Controller {
	
	@ModelAttribute
	public Calc1Form setUpform() {
		Calc1Form form = new Calc1Form();
		form.setDeck(40);
		form.setTarget(4);
		form.setDraw(5);
		form.setWant(1);
		return form;
	}
	
	@GetMapping
	public String form(Model model) {
		model.addAttribute("title", "CardCalc | Calc1");
		return "calc1";
	}
}
