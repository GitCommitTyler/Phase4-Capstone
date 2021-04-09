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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Music;
import com.example.Capstone.services.AlbumService;
import com.example.Capstone.services.MusicService;




@Controller
public class HomeController {
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private MusicService musicService;
	
	
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
	
	@RequestMapping(value="/user/album/{id}", method=RequestMethod.GET)
	public String showAlbumPage(@PathVariable(value="id") String id, Model model) {
		logger.info(id);
		try {
			Album album = albumService.getAlbumById(Long.parseLong(id));
			Iterable<Music> trackList = musicService.GetMusicByAlbum(album);
			model.addAttribute("album", album);
			model.addAttribute("tracklist", trackList);
			return "user/albumpage";
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "user/albumpage";
	}
	
	
}
