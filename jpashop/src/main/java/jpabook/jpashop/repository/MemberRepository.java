package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;

//스프링 빈으로 등록, JPA 예외를 스프링 기반 예외로 예외 변환
@Repository
public class MemberRepository {

	//스프링이 엔티티 매니저 주입. 스프링 데이터 JPA면 @Autowired가 적용됨
	@PersistenceContext	
	private EntityManager em;
	
	public void save(Member member) {
		em.persist(member); //영속성 컨텍스트에 넣어서 insert를 시킴
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);	
	}
	
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList(); //from의 대상은 테이블이 아니라 엔티티라는게 JPL의 차이점
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	
}
