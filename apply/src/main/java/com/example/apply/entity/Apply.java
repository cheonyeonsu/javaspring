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

@Entity
@Table(name = "apply")
@Getter
@Setter
@ToString
public class Apply {

	//관계성 만들어주기. 수강신청 식별자. 
	@Id //기본키.
	@Column(name="apply_id") //db에 저장되는 이름 지정. 
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 자동 지정
	private Long id; //수강신청 식별자
	
	//학생 식별자
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; //필드에 엔티티 넣어줌
	
	//과목 식별자
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
}
