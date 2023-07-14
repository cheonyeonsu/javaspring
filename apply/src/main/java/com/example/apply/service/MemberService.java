package com.example.apply.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.entity.Member;
import com.example.apply.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional //쿼리문 수행 시 에러가 발생하면 데이터를 이전상태로 콜백시켜줌. 
@RequiredArgsConstructor //@Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다. 
public class MemberService {
	private final MemberRepository memberRepository;
	
	//회원가입 데이터 db에 저장
	public Member saveMember(Member member) {
		vailidateDuplicateMember(member);
		Member savedmember = memberRepository.save(member);
		return savedmember;
	}
	
	//이메일 중복 체크
	private void vailidateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
	
		if(findMember !=null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	
	}
}
