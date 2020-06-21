package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	//TODO JPA 스펙 상 필드 생성자를 사용하려면 기본 생성자가 필요하다.
	protected Address() {
		
	}
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
}
