package com.example.apply.entity;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Role;
import com.example.apply.dto.MemberFormDto;
import com.myshop.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 엔티티 클래스로 정의. 꼭 있어야 함.
@Table(name = "member") // 테이블 이름 지정
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
	@Id // 프라이머리라서 아이디 줘야 함.
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	private int number;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	@Enumerated(EnumType.STRING)
	private Role role;

	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

		Member member = new Member();

		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setNumber(memberFormDto.getNumber());

		// pw 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());

		// member.setRole(Role.ADMIN); //관리자로 가입할 때
		member.setRole(Role.USER); // 일반 사용자로 가입할 때.

		return member;
	}

}
