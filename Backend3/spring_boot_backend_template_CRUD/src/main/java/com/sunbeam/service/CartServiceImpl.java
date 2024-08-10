package com.sunbeam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.dao.CartDao;
import com.sunbeam.entities.Cart;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	@Override
	public Cart saveCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDao.save(cart);
	}
	
	

}
