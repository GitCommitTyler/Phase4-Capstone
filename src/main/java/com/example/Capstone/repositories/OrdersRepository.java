package com.example.Capstone.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;

public interface OrdersRepository extends CrudRepository<Orders, Integer>{
	
	public Orders findById(int order_id);

	public Iterable<Orders> findAllByUser(User user);

}
