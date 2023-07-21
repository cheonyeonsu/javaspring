package com.example.apply.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.service.SubjectService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ApplyController {

	private final SubjectService subjectService; //의존성 줌
	
	@GetMapping(value={"/apply/new", "/apply/new/{page}"}) //경로
	public String applyForm(Model model, SubjectSearchDto subjectSearchDto, Optional<Integer> page ) {
		Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, 5); 
		Page<SubjectDto> subjects = subjectService.getApplyPage(subjectSearchDto, pageable);
		
		model.addAttribute("subjects",subjects);
		model.addAttribute("subjectSearchDto",subjectSearchDto);
		model.addAttribute("maxPage",5);
		
		return "apply/applyForm"; //리턴할 파일 이름
	}
	
	
}
