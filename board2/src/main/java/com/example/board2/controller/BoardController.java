package com.example.board2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	@RequestMapping(value="/created", method=RequestMethod.GET) //localhost로 접속.  get방식으로 들어왔을 때 아래 메소드 실행시키겠오
	public String created() {
		return "bbs/created";
	}
	
	@RequestMapping(value="/") //localhost로 접속.  getmapping을 이렇게 하면은 최상단으로 감. 
	public String index() {
		return "/list";
	}
	
	@RequestMapping(value="/article", method=RequestMethod.GET) 
	public String article() {
		return "bbs/article";
	}
	
	@RequestMapping(value="/updated", method=RequestMethod.GET) 
	public String updated() {
		return "bbs/updated";
	}
	
}
