package com.sunbeam.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_id")
    private Long deliveryId;
    
	@Column(name = "del_status")
    private Boolean delStatus;
	
	@Column(name = "total_amount")
    private Float totalAmount;
	
	@Column(name = "del_datetime")
    private LocalDate delDatetime;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    
    public Delivery() {
		// TODO Auto-generated constructor stub
	}

	public Delivery(Long deliveryId, Boolean delStatus, Float totalAmount, LocalDate delDatetime, Order order,
			User user) {
		super();
		this.deliveryId = deliveryId;
		this.delStatus = delStatus;
		this.totalAmount = totalAmount;
		this.delDatetime = delDatetime;
		this.order = order;
//		this.user = user;
	}

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Boolean getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Boolean delStatus) {
		this.delStatus = delStatus;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getDelDatetime() {
		return delDatetime;
	}

	public void setDelDatetime(LocalDate delDatetime) {
		this.delDatetime = delDatetime;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
 
}
