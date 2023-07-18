package com.example.apply.dto;

import com.example.apply.entity.ApplyClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyClassDto {

	public ApplyClassDto(ApplyClass applyClass, String string) {
		this.applyName = applyClass.
		this.applyDate
		this.applyStatus
		this.applyTo
	}
	
	
	
	private String applyName; //신청자 명
	
	private int applyDate; //신청일
	
	private String applyStatus; //신청 상태
	
	private int applyTo; //신청 가능 인원
}
