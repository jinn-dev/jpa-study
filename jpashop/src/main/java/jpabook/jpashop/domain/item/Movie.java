package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("M")	//TODO 싱글테이블의 구분값
public class Movie extends Item {
	private String director;
	private String actor;
}
