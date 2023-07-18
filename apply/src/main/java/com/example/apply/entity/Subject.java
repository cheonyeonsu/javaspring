package com.example.apply.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "subject")
@Getter
@Setter
@ToString
//지정된 과목. = 수강신청할 수 있는 과목. 
public class Subject {
	@Id //기본키.
	@Column(name="subject_id") //db에 저장되는 이름 지정. 
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 자동 지정
	private Long id;
	
	//과목 이름
	private String subjectName;
	
	//수업 시간
	private String subjecttime;
	
	//수업 설명
	private String subjectdetail;
	
}
