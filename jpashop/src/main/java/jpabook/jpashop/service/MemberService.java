package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) //JPA ��ȸ�� ���� ����ȭ (���Ӽ��� ��Ƽ üŷ ����, DB�� ���� �б� �����̸�  ���ҽ� ���� ���)
@RequiredArgsConstructor
public class MemberService {
	
	//1. �ʵ� ���� - �߰��� ���ٲ۴ٴ� ����
	//2. setter ���� - �ٷ� ������ �����༭ �׽�Ʈ �ڵ� �ۼ� �� ���� �������� �� �ִ�. ������ ���� ���� � �߿� �ٲ����� ���� ���� ������ ����
	//3. ������ ���� - �ѹ� ������ �� �ϼ��� �ǹǷ� �߰��� �ٲ� �� ���� �׽�Ʈ ���̽� �ۼ� �� ���� ������ ����� �ؼ� �������� ��Ȯ�ϰ� �� �� ����.
	//3-1. �ֽŹ����� ��  ������ �ϳ� ���� ��� @Autowired ���� ����. ������ ������ ���� ���θ� �� �� �ֱ� ������ final�� ����. 
	//3-2. @RequiredArgsConstructor�� final �ִ� �͸� ������ �ڵ� �����ǹǷ� ������ �ۼ� ���ص� ��
	
	private final MemberRepository memberRepository;
	
	/* 
	 * ȸ������ 
	 * */
	@Transactional
	public Long join(Member member) {
		validateDupMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	/*
	 * �ߺ�ȸ�� ����
	 * */
	private void validateDupMember(Member member) {
		//Exception ó��
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
		}	
	}
	
	/*
	 * ȸ�� ��ü ��ȸ
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	/*
	 * ȸ�� �ܰ� ��ȸ
	 */
	public Member findMember(Long id) {
		return memberRepository.findOne(id);
	}
	
}
