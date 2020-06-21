package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	//TODO JPA ���� �� �ʵ� �����ڸ� ����Ϸ��� �⺻ �����ڰ� �ʿ��ϴ�.
	protected Address() {
		
	}
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
}