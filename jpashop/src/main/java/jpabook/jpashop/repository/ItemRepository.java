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
	 * ��ǰ ����
	 */
	public void save(Item item) {
		if(item.getId() == null) {	//id�� null�̸� �ű� ��ü�̹Ƿ� persist
			em.persist(item);
		} else {
			em.merge(item);			//���� ��ü�� update�ϴ� �Ͱ� �����
		}
	}
	
	/*
	 * ��ǰ �ܰ� ��ȸ
	 */
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	/*
	 * ��ǰ ��ü ��ȸ
	 */
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
	
}
