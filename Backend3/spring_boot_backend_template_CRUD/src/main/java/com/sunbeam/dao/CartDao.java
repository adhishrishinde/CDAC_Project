package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Cart;
import com.sunbeam.entities.Category;

public interface CartDao extends JpaRepository<Category, Long> {

	Cart save(Cart cart);

}
