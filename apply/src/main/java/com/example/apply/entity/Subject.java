package com.example.apply.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	//과목명
	private String subjectName;
	
	//개강 일자
	private LocalDateTime subjectStartDate;
	
	//종강 일자
	private LocalDateTime subjectEndDate;
	
	//과목 설명
	private String subjectdetail;
	
	//수강 가능 인원
	private int subjectTo;
	
	

}
