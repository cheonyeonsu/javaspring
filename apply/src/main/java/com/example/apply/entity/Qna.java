package com.example.apply.entity;


import java.time.LocalDateTime;

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

@Entity // 엔티티 클래스로 정의. 꼭 있어야 함.
@Table(name = "qna") // 테이블 이름 지정.
@Getter
@Setter
@ToString
public class Qna {
	
	@Id //기본키.
	@Column(name="qna_id") //db에 저장되는 이름 지정. 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; //글 번호
	
	@Column(nullable = false)
	private String title; //글 제목

//	@Column(nullable = false)
//	private String userId; //작성자
	
	@Column(nullable = false)
	private LocalDateTime date; //작성일
	
	@Column(nullable = false)
	private String content; //글 내용

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; 

}









