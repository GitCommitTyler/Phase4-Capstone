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
		List<Album> newAlbums = (List<Album>)albumService.getNewAlbums(getDateThreshhold());
		double itemsPerSlide = 5;
		double pages = Math.ceil(newAlbums.size()/itemsPerSlide);
		logger.info(Double.toString(pages));
		model.addAttribute("pages", pages);
		model.addAttribute("itemsPerSlide", itemsPerSlide);
		model.addAttribute("newAlbums", newAlbums);
		return "user/index";
	}
	
	
}
