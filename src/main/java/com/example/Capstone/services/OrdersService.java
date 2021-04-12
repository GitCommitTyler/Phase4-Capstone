package com.example.Capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;
import com.example.Capstone.repositories.OrdersRepository;

@Service
public class OrdersService {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	UserService userService;
	
	public Iterable<Orders> GetOrdersByUser(User user){
		return(ordersRepository.findAllByUser(user));
	}
	
	public Orders getOrdersById(int id) {
		return(ordersRepository.findById(id));
	}
	
	public Orders addOrder(Orders order) {
		return ordersRepository.save(order);
	}
	
	public Orders updateOrder(Orders orderToUpdate) {
		return ordersRepository.save(orderToUpdate);
	}
	
	public void deleteOrder(int id) {
		ordersRepository.deleteById(id);
	}

	public Iterable<Orders> findAllOrders() {
		// TODO Auto-generated method stub
		return ordersRepository.findAll();
	}
	public void save(Orders o)
	{
		ordersRepository.save(o);
	}
}
