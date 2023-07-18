package com.example.apply.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apply.entity.Member;
import com.example.apply.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional //쿼리문 수행 시 에러가 발생하면 데이터를 이전상태로 콜백시켜줌. 
@RequiredArgsConstructor //@Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다. 
public class MemberService implements UserDetailsService {
	private final MemberRepository memberRepository;
	
	//회원가입 데이터 db에 저장
	public Member saveMember(Member member) {
		vailidateDuplicateMember(member); //중복 확인
		Member savedmember = memberRepository.save(member);
		return savedmember;
	}
	
	//이메일 중복 체크. 이미 가입된 회원인 경우 IllegalStateException 예외 발생
	private void vailidateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByUserId(member.getUserId());
		
		if(findMember !=null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	
		
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Member member = memberRepository.findByUserId(userId);
		
		if (member == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		//사용자가 있다면 userDetails 객체를 만들어서 반환. 
		return User.builder()
				.username(member.getUserId()) //username = id개념. 
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
}
