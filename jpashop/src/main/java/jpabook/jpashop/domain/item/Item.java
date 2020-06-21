package jpabook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)	//TODO 상속관계 전략 잡기
@DiscriminatorColumn(name = "dtype")					//TODO 싱글테이블 전략으로 각 아이템의 구분자 사용
@Entity
@Getter @Setter
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<Category>();
	
}
