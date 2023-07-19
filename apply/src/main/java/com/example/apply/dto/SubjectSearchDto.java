package com.example.apply.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectSearchDto {
	private String searchDateType;
	private String searchQuery = "";
}
