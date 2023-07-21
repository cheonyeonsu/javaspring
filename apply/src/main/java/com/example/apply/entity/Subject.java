package com.example.apply.entity;

import java.time.LocalDateTime;

import com.example.apply.dto.SubjectDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "subject")
@Getter
@Setter
@ToString
public class Subject {
	
	@Id //기본키.
	@Column(name="subject_id") //db에 저장되는 이름 지정. 
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 자동 지정
	private Long subjectId;
	
	@Column(nullable = false,length = 50)
	private String subjectName; //과목명
	
	@Column(nullable = false)
	private LocalDateTime subjectStartDate; //개강 일자
	
	@Column(nullable = false)
	private LocalDateTime subjectEndDate; //종강 일자
	
	@Lob
	@Column(nullable = false, columnDefinition = "longtext")
	private String subjectDetail; //과목 설명
	
	@Column
	private int subjectTo; //수강 가능 인원
	
	//subject 엔티티 수정
	public void updateSubject(SubjectDto subjectDto) {
		this.subjectName = subjectDto.getSubjectName();
		this.subjectStartDate = subjectDto.getSubjectStartDate();
		this.subjectEndDate = subjectDto.getSubjectEndDate();
		this.subjectDetail = subjectDto.getSubjectDetail();
		this.subjectTo = subjectDto.getSubjectTo();
	}
	

}
