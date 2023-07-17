package com.example.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "apply")
@Getter
@Setter
@ToString
public class Apply {

	@Id
	@Column(name="")
	@GeneratedValue
	private Long apply; //수강신청 식별자
	
	private String name; //신청자 명
	
	private int date; //신청일
	
	private String status; //신청 상태
	
	private int to; //신청 가능 인원
	
}
