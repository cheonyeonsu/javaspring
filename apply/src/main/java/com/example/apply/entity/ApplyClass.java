package com.example.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity (name = "apply_class")
@Table
@Getter
@Setter
@ToString
public class ApplyClass {
	
	@Id
	@Column(name="apply") //수강신청 식별자.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; //학생 식별자
	
	//신청자명
	
	//신청일
	
	//신청상태 
	
	//수강 가능 인원
	
	
}
