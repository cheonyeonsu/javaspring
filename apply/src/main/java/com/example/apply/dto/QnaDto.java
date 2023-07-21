package com.example.apply.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDto {
	
	private long id; //글 번호
	

	private String title; //글 제목
	

	//private String userId; //작성자
	

	private LocalDateTime date; //작성일
	

	private String content; //글 내용

}
