package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) //JPA 조회의 성능 최적화 (영속성의 더티 체킹 안함, DB에 따라 읽기 전용이면  리소스 절약 등등)
@RequiredArgsConstructor
public class MemberService {
	
	//1. 필드 주입 - 중간에 못바꾼다는 단점
	//2. setter 주입 - 바로 주입을 안해줘서 테스트 코드 작성 시 목을 주입해줄 수 있다. 단점은 실제 정상 운영 중에 바꿀일이 전혀 없고 오히려 위험
	//3. 생성자 주입 - 한번 생성할 때 완성이 되므로 중간에 바꿀 수 없고 테스트 케이스 작성 시 직접 주입을 해줘야 해서 의존성을 명확하게 알 수 있음.
	//3-1. 최신버전일 때  생성자 하나 있을 경우 @Autowired 생략 가능. 컴파일 시점에 주입 여부를 알 수 있기 때문에 final도 적용. 
	//3-2. @RequiredArgsConstructor는 final 있는 것만 생성자 자동 생성되므로 생성자 작성 안해도 됨
	
	private final MemberRepository memberRepository;
	
	/* 
	 * 회원가입 
	 * */
	@Transactional
	public Long join(Member member) {
		validateDupMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	/*
	 * 중복회원 검증
	 * */
	private void validateDupMember(Member member) {
		//Exception 처리
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}	
	}
	
	/*
	 * 회원 전체 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	/*
	 * 회원 단건 조회
	 */
	public Member findMember(Long id) {
		return memberRepository.findOne(id);
	}
	
}
