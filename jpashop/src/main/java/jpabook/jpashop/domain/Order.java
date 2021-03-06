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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;	
	
	@ManyToOne(fetch = FetchType.LAZY)	//모든 연관관계는 반드시 지연로딩으로 설정
	@JoinColumn(name = "member_id")
	private Member member;
	
	//모든 엔티티는 persist를 각각 해줘야하는데 cascade를 활용하면 한꺼번에 persist 된다.
	//컬렉션은 필드에서 초기화하자(null 안전, 하이버네이트)
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) 
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate;	
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	//연관관계 메서드(양방향 관계일 때 핵심적으로 컨트롤 하고 있는쪽에 생성)
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
	
	//생성 메서드
	public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		for(OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		
		return order;
	}
	
	//비즈니스 로직
	/*
	 * 주문 취소
	 */
	public void cancel() {
		if(delivery.getStatus() == DeliveryStatus.COMPLETE) {
			throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
		}
		
		this.setStatus(OrderStatus.CANCEL);
		
		for(OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}
	
	//조회 로직
	/*
	 * 전체 주문 가격 조회
	 */
	public int getTotalPrice() {
		int totalPrice = 0;
		
		for(OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		
		return totalPrice;
		
		//return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
	}
}
