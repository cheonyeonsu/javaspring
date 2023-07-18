package com.example.apply.entity;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Role;
import com.example.apply.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 엔티티 클래스로 정의. 꼭 있어야 함.
@Table(name = "member") // 테이블 이름 지정. erd
@Getter
@Setter
@ToString
public class Member {
	@Id // 프라이머리라서 아이디 줘야 함.
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true) //회원을 아이디로 구분해야해서 동일한 값 못들어오게 유니크 속성 지정. 
	private String userId;

	private String email;

	private String password;

	private String phone;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	@Enumerated(EnumType.STRING)
	private Role role;

	//멤버 앤티티를 생성하는 메소드. 
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

		Member member = new Member();

		member.setUserId(memberFormDto.getUserId());
		member.setEmail(memberFormDto.getEmail());
		member.setPhone(memberFormDto.getPhone());

		// 비밀번호 암호화
		String password = passwordEncoder.encode(memberFormDto.getPassword());
		member.setPassword(password);

		// member.setRole(Role.ADMIN); //관리자로 가입할 때
		member.setRole(Role.USER); // 일반 사용자로 가입할 때.

		return member;
	}

}
