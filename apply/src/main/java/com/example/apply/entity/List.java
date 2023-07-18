package com.example.apply.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "list")
@Getter
@Setter
@ToString
public class List {
	
	@Id //기본키.
	@Column(name="list_id") //db에 저장되는 이름 지정. 
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 자동 지정
	private Long id;
	
	//수업 이름
	private String className;
	
	//수업 시간
	private String classtime;
	
	//수업 설명
	private String classdetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_id")
	private Apply apply;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
}
