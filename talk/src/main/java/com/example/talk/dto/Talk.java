package com.example.talk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Talk {
	private int num; //게시글 번호
	private String name;
	private String pwd;
	private String subject;
	private String content;
	private int hitCount; //조회수
	private String created; //게시물 생성날짜
}
