package com.example.apply.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.apply.dto.QnaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QnaController {
	
	
	@GetMapping(value="/qna/list") //검색창에 나오는 경로.
	public String list() {
		return "qna/qnaList"; //진짜 경로 > 대소문자 구분 확실히 하기. 
	}
	
	@GetMapping(value="/qna/regist")
	public String regist() {
		return "qna/qnaRegist";
	}
	
	@PostMapping(value="/qna/regist") 
	public String addRegist(@Valid QnaDto qnaDto) {
		//등록하기
		
		return "qna/qnaRegist";
	}
}
