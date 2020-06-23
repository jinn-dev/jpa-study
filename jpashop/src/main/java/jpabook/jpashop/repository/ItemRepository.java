package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	
	private final EntityManager em;
	
	/*
	 * 상품 저장
	 */
	public void save(Item item) {
		if(item.getId() == null) {	//id가 null이면 신규 객체이므로 persist
			em.persist(item);
		} else {
			em.merge(item);			//기존 객체를 update하는 것과 비슷함
		}
	}
	
	/*
	 * 상품 단건 조회
	 */
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	/*
	 * 상품 전체 조회
	 */
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
	
}
