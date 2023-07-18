package com.example.apply.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.apply.dto.MemberFormDto;
import com.example.apply.entity.Member;
import com.example.apply.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
		
	@GetMapping(value="/members/qa")
	public String qa() {
		return "member/qa";
	}
	
	//로그인 화면
	@GetMapping(value="/members/login")
	public String longinMember() {
		return "member/memberLoginForm";
	}
	
	//회원가입 화면 띄움
	@GetMapping(value="/members/new")
	public String memberForm(Model model) {
		MemberFormDto memberFormDto = new MemberFormDto();
		model.addAttribute("memberFormDto", memberFormDto);
		
		return "member/memberForm";
	}
	
	//회원가입
	@PostMapping(value="/members/new")
	public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder); //PasswordEncoder > 패스워드를 암호화
			memberService.saveMember(member);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage",e.getMessage());
			return "member/memberForm";
		}
		
		return "redirect:/";
	}
	
	//로그인 실패했을 때 
		@GetMapping(value="/members/login/error")
		public String loginError(Model model) {
			model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요."); //멤버로그인폼으로 넘어감. 
			return "/member/memberLoginForm";
		}
}
