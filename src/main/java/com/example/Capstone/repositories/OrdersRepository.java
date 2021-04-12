package com.example.Capstone.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;
@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer>{
	
	public Orders findById(int order_id);

	public Iterable<Orders> findAllByUser(User user);

}
