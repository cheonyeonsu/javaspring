package com.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.dto.Dept;
import com.springproject.service.DeptService;

@RestController //
public class DeptController {
	@Autowired
	   DeptService deptService;
	  
	   @GetMapping("/main") //경로를 만들어줌. 주소 뒤에 main 붙여줘야 접속 됨
	   public List<Dept> main() {
	       List<Dept> list = deptService.selectList();
	       return list;
	   }

}
