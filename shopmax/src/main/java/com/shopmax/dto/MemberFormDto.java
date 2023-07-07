package com.shopmax.dto;


import org.hibernate.validator.constraints.Length;

import com.shopmax.constant.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter//위치 주의.
@Setter
public class MemberFormDto {

	@NotBlank(message = "이름은 필수 입력 값입니다. ")
	private String name;
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다. ")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다. ")
	@Length(min = 8, max = 16, message="비밀번호는 8~16자 사이로 입력해주세요.")
	private String password;
	
	@NotEmpty(message = "주소는 필수 입력 값입니다. ")
	private String address;
	
	
	private Role role;
}