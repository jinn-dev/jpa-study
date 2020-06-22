package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;	
	
	@ManyToOne(fetch = FetchType.LAZY)	//��� ��������� �ݵ�� �����ε����� ����
	@JoinColumn(name = "member_id")
	private Member member;
	
	//��� ��ƼƼ�� persist�� ���� ������ϴµ� cascade�� Ȱ���ϸ� �Ѳ����� persist �ȴ�.
	//�÷����� �ʵ忡�� �ʱ�ȭ����(null ����, ���̹�����Ʈ)
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) 
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate;	
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	//�������� �޼���(����� ������ �� �ٽ������� ��Ʈ�� �ϰ� �ִ��ʿ� ����)
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}
