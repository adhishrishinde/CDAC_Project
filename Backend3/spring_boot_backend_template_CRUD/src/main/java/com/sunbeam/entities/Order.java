package com.sunbeam.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "order_id")
	 private Long orderId;
	 
	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Item> items;
	 
//	 @Column(name = "total_amount")
//	 private Float totalAmount;
//	 
//	 @Column(name = "o_date")
//	 private LocalDate odate;
//	 
//	 @Column(name = "order_quantity")
//	 private Integer orderQuantity;

//	 @ManyToOne
//	 @JoinColumn(name = "user_id")
//	 private User user;

//	 @OneToOne(mappedBy = "order")
//	 private Delivery delivery;

//	 @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//	 private Payment payment;
//
//	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//	 private List<Cart> cartItems;  

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Long orderId, Set<Item> items) {
		super();
		this.orderId = orderId;
		this.items = items;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Set<Item> getItems() {
		return items;
	}
	
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	


}
