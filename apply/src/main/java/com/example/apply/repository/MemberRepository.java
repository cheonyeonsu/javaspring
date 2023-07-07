package com.example.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Member;

public interface MemberRepository extends JpaRepository<Member, >{

}
