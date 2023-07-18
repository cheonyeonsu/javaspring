package com.example.apply.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyClassDto {

	private String applyName; //신청자 명
	
	private int applyDate; //신청일
	
	private String applyStatus; //신청 상태
	
	private int applyTo; //신청 가능 인원
}
