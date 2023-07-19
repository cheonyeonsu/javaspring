package com.example.apply.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.apply.dto.SubjectDto;
import com.example.apply.dto.SubjectSearchDto;

public interface SubjectRepositoryCustom {

	Page<SubjectDto> getApplyPage(SubjectSearchDto subjectSearchDto, Pageable pageable);
}
