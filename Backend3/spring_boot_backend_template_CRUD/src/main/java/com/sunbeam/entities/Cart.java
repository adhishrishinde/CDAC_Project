package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
    
//    @Column(name = "item_name")
//    private String itemName;
//    
//    @Column(name = "item_quantity")
//    private Integer itemQuantity;

//    @ManyToMany
//    @JoinTable(
//        name = "cart_items",
//        joinColumns = @JoinColumn(name = "cart_id"),
//        inverseJoinColumns = @JoinColumn(name = "item_id")
//    )
//    private Set<Item> items;
    
    @OneToMany(mappedBy = "cart",
			cascade = CascadeType.ALL,orphanRemoval = true) 
	private List<Item> items = new ArrayList<>();
    
//    @OneToMany(mappedBy= "cart", cascade = CascadeType.ALL, orphanRemoval = true)
////    @JoinColumn(name = "cart_id") // This is needed to define the foreign key in Item table
//    private Set<Item> items = new HashSet<>();
    
//    @OneToMany(mappedBy = "cartItem")
//	private Set<Item> items;


//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    
//    @ManyToOne
//    @JoinColumn(name = "orderId")
//    private Order order;
    
    public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(Long cartId, List<Item> items) {
		super();
		this.cartId = cartId;
		this.items = items;
	}
	
	public Long getCartId() {
		return cartId;
	}
	
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	


//	public List<Item> getItems() {
//		return items;
//	}
//
//
//
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}
//
//

	
    
    
}
