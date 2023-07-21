package com.example.apply.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;
import com.example.apply.repository.SubjectRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {
	
	private final SubjectRepository subjectRepository;
	
	@Transactional(readOnly = true)
	public Page<SubjectDto> getApplyPage(SubjectSearchDto subjectSearchDto, Pageable pageable){
		Page<SubjectDto> applyPage = subjectRepository.getApplyPage(subjectSearchDto, pageable);
		return applyPage;
		
	}
	
	
}
