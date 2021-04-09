package com.example.Capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;
import com.example.Capstone.services.OrdersService;
import com.example.Capstone.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class AccountController {
	@Autowired
	UserService userService;
	
	@Autowired
    OrdersService ordersService;
	
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
	
}
