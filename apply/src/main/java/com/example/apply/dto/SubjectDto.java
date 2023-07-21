package com.example.apply.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {

	private Long id;

	// 과목명
	private String subjectName;

	// 개강 일자
	private LocalDateTime subjectStartDate;

	// 종강 일자
	private LocalDateTime subjectEndDate;

	// 과목 설명
	private String subjectDetail;

	// 수강 가능 인원
	private int subjectTo;
	
	@QueryProjection
	public SubjectDto(Long id, String subjectName,LocalDateTime subjectStartDate,
					  LocalDateTime subjectEndDate,String subjectDetail,int subjectTo) {
		this.id = id;
		this.subjectName = subjectName;
		this.subjectStartDate = subjectStartDate;
		this.subjectEndDate= subjectEndDate;
		this.subjectDetail=subjectDetail;
		this.subjectTo = subjectTo;
		
	}


}
