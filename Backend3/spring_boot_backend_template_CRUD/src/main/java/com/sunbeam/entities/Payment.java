package com.sunbeam.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    
	@Column(name = "total_amount")
    private Float totalAmount;
    
    @Column(name = "payment_type")
    private String paymentType;
    
    @Column(name = "payment_status")
    private Boolean paymentStatus;
    
    @Column(name = "pdatetime")
    private LocalDateTime pdatetime;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    
    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;
    
    public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(Long paymentId, Float totalAmount, String paymentType, Boolean paymentStatus,
			LocalDateTime pdatetime, User user, Delivery delivery) {
		super();
		this.paymentId = paymentId;
		this.totalAmount = totalAmount;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.pdatetime = pdatetime;
//		this.user = user;
		this.delivery = delivery;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPdatetime() {
		return pdatetime;
	}

	public void setPdatetime(LocalDateTime pdatetime) {
		this.pdatetime = pdatetime;
	}

}
