package com.example.Capstone.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Music;
import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;
import com.example.Capstone.services.MusicService;
import com.example.Capstone.services.OrdersService;
import com.example.Capstone.services.AlbumService;
import com.example.Capstone.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class AccountController {
	@Autowired
	UserService userService;
	@Autowired
	MusicService musicService;
	@Autowired
    OrdersService ordersService;
	@Autowired
	AlbumService albumService;
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	@GetMapping(value="/user/account")
	public String getAccountInfo(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		
		////////////
		Iterable<Orders> orders = ordersService.GetOrdersByUser(user);
		model.addAttribute("orders",orders);
		///////////////
		model.addAttribute("user",user);
		return "user/account";
	}
	@PostMapping(value="/user/updateAccountInfo")
	public String updateAccountInfo(@RequestParam("name") String name,
			@RequestParam("last_name") String last_name, @RequestParam("email") String email,Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		User updatedUser= userService.updateInfo(user,name,last_name,email);
		if(updatedUser==null) {
			model.addAttribute("message","Updating Info was Unsuccessful,Please check your Inputs");
			model.addAttribute("user",updatedUser);
		}
		else {
			model.addAttribute("user",user);
			model.addAttribute("message","Your Account Info has been updated");
		}
		return"user/account";
	}
	@PostMapping(value="/user/updateAccountPassword")
	public String updateAccountPassword(@RequestParam(value="password") String password,
			@RequestParam(value="confirmPassword") String confirmPassword,Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		User updatedUser= userService.updatePassword(user,password,confirmPassword);
		if(updatedUser==null) {
			model.addAttribute("message2","Updating Password was Unsuccessful,Please check your Password Inputs");
		}
		else {
			model.addAttribute("message2","Updating Password was Successful");
		}
		return"user/account";
	}
//	@GetMapping(value="/user/checkout")
//	public String goToCheckOut(ModelMap model){
//		
//		return"user/checkout";
//	}
	@GetMapping(value="/user/checkout")
	public String createCheckout(@RequestParam (value="tracks") String[] tracks, @RequestParam (value="albums") String[] albums,ModelMap model ) {
		List<Music> cartTracks=new LinkedList<Music>();
		List<Album> cartAlbums=new LinkedList<Album>();
		for(String str:tracks) {
			cartTracks.add(musicService.GetMusicById(Long.parseLong(str)).get());	
		}
		for(String str:albums) {
			try {
				cartAlbums.add(albumService.getAlbumById(Long.parseLong(str)));	
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("albums",cartAlbums);
		model.addAttribute("tracks",cartTracks);
		return "user/checkout";
	}
	
}
