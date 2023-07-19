package com.example.apply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplyController {

	@GetMapping(value="/apply/new") //경로
	public String applyForm() {
		return "apply/applyForm"; //파일 이름
	}
	
	
}
