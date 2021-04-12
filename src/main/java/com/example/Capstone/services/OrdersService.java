package com.example.Capstone.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Music;
import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;
import com.example.Capstone.repositories.OrdersRepository;

@Service
public class OrdersService {
	
	private List<Music> tracks;
	private List<Album> albums;
	private String total;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	UserService userService;
	
	public void saveInfo(List<Music> tr,List<Album> al,String tot) {
		this.tracks=tr;
		this.albums=al;
		this.total=tot;
	}
	public String getTotal() {
		return total;
	}
	public List<Music> getTracks(){
		return tracks;
	}
	public List<Album> getAlbums(){
		return albums;
	}
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
	public Orders createOrder(List<Album> albums,List<Music> tracks,String total) {
		String albumStr="";
		String trackStr="";
		for(Album album:albums) {
			albumStr+=album.getName();
			albumStr+=", ";
		}
		albumStr=albumStr.substring(0,albumStr.length()-1);
		for(Music track:tracks) {
			trackStr+=track.getName();
			trackStr+=",";
		}
		trackStr=trackStr.substring(0,trackStr.length()-1);
		Float newTotal= Float.parseFloat(total);
		Date date = new Date();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Orders order= new Orders( date,user,"Delivered",albumStr,trackStr,newTotal);
		return ordersRepository.save(order);
	}
}
