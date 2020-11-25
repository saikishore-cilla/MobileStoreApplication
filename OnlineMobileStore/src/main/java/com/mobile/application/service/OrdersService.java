package com.mobile.application.service;


import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.application.model.Cart;
import com.mobile.application.model.Orders;
import com.mobile.application.repository.OrdersRepository;

@Service
@Transactional

public class OrdersService {
	@Autowired
	private OrdersRepository orderRepo;

	public OrdersService(OrdersRepository orderRepo) {
		
		this.orderRepo = orderRepo;
	} 

	public void saveOrders(Orders order,Cart c ) {
		
		order.setModel(c.getModel());
		int t=(int) (c.getPrice() * c.getQuantity());
		order.setTotal(t);
		
		orderRepo.save(order);
	}

	/*
	 * public Optional<Orders> getById(String email) { // TODO Auto-generated method
	 * stub return orderRepo.findById(email);
	 * 
	 * }
	 */

	public List<Orders> getAllOrders() {
	List<Orders>l= (List<Orders>) orderRepo.findAll();
	return l;
	}
	

}
