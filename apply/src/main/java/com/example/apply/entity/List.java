package com.example.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class List {

	//수업 이름
	@Id
	private String className;
	
	//수업 시간
	private String time;
	
	//수업 설명
	private String detail;
	
}
