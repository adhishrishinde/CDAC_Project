package com.sunbeam.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "phone_no" , length = 10)
	private String phoneNo;
	
	//@Column(length = 100)
	private String password;
	
	private String address;
	
//	@OneToMany(mappedBy = "user")
//    private List<Order> orders;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // This is needed to define the foreign key in Order table
    private Set<Order> orders = new HashSet<>();

//    @OneToMany(mappedBy = "user")
//    private List<Delivery> deliveries;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // This is needed to define the foreign key in Payment table
    private Set<Delivery> deliveries = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id") // This is needed to define the foreign key in Payment table
    private Set<Payment> payments = new HashSet<>();
	
//    @OneToMany(mappedBy = "user")
//    private List<Payment> payments;

}
