package com.example.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
	//회원가입 시 중복된 회원이 있는지 검사하기 위해서. 
	//이메일로 검사할 수 있도록.
	Member findByEmail(String email);
	Member findByUserId(String userId);
}
