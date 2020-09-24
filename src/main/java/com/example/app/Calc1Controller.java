package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Calc1")
public class Calc1Controller {
	@GetMapping
	public String form(Model model) {
		model.addAttribute("title", "CardCalc | Calc1");
		return "calc1";
	}
}
