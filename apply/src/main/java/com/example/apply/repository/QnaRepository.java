package com.example.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna,Long>{

	//extends받아야하니까 Qna 생성. ★클래스 이름은 대문자 기입★ 
}
