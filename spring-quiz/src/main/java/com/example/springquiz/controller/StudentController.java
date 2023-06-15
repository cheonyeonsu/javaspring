package com.example.springquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springquiz.dto.Student;
import com.example.springquiz.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	
	 @GetMapping("/student")
	public List<Student> main(){
		 List<Student> list = studentService.selectList();
		 return list;
	 }
}
