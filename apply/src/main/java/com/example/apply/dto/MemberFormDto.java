package com.example.apply.dto;

import javax.management.relation.Role;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

	@NotBlank(message = "아이디는 필수 입력 값입니다. ")
	private String userId;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다. ")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다. ")
	@Length(min = 8, max = 16, message="비밀번호는 8~16자 사이로 입력해주세요.")
	private String password;
	
	@NotEmpty(message = "번호는 필수 입력 값입니다. ")
	private String phone;
	
	private Role role;
	
	
}
