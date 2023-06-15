package com.example.springquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springquiz.dao.StudentDao;
import com.example.springquiz.dto.Student;

@Service
public class StudentServiceimpl implements StudentService{

	@Autowired
	StudentDao studentDao;
	
	@Override
	public List<Student> selectList() {
		return studentDao.selectList();
	}
 
}
