package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	/*
	 * ��ǰ ����
	 */
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	/*
	 * ��ǰ ��ü ��ȸ
	 */
	public List<Item> findITems() {
		return itemRepository.findAll();
	}
	
	/*
	 * ��ǰ �ܰ� ��ȸ
	 */
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
}
