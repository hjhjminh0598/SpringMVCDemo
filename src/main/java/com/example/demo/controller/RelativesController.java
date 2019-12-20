package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelativesController {
	@GetMapping("/relatives")
	public String project(Model model) {
		return "relatives";
	}
}
