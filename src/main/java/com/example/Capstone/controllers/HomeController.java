package com.example.Capstone.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Capstone.entities.Album;
import com.example.Capstone.services.AlbumService;




@Controller
public class HomeController {
	
	@Autowired
	private AlbumService albumService;
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public LocalDate getDateThreshhold() {
		LocalDate dateNow = LocalDate.now();
		LocalDate recent = dateNow.minusWeeks(104);
		logger.info(recent.toString());
		return recent;
	}
	
	@RequestMapping(value= "/user/index", method= RequestMethod.GET)
	public String getNewMusic(Model model) {
		Iterable<Album> newAlbums = albumService.getNewAlbums(getDateThreshhold());
		model.addAttribute("newAlbums", newAlbums);
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		model.addAttribute("numbers", numbers);
		numbers.forEach(x->logger.info(x.toString()));
		return "user/index";
	}
	
	
}
