package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssignedController {
	@GetMapping("/assigned")
	public String project(Model model) {
		return "assigned";
	}
}
