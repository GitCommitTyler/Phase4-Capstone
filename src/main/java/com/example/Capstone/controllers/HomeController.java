package com.example.Capstone.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Music;
import com.example.Capstone.services.AlbumService;
import com.example.Capstone.services.GenreService;
import com.example.Capstone.services.MusicService;




@Controller
public class HomeController {
	
	@Autowired
	private GenreService genreService;
	
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
		model.addAttribute("newAlbums", newAlbums);
		model.addAttribute("genres", genreService.GetAllGenre());
		model.addAttribute("artists", albumService.getArtists());
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
	
	@RequestMapping(value="/user/allalbums", method=RequestMethod.GET)
	public String showAllAlbums(@RequestParam("criterion")String criterion, @RequestParam("value")String value, Model model) {
		Iterable<Album> allAlbums = albumService.getAlbums();
		List<Album> filteredAlbums = new ArrayList<Album>();
		allAlbums.forEach(x->logger.info(x.getGenre().toString()));
		switch(criterion.toLowerCase()) {
		case(""):
			model.addAttribute("albumsToShow", allAlbums);
			model.addAttribute("pagetitle", "All Albums");
			break;
		case("genre"):
			model.addAttribute("pagetitle", genreService.GetGenre(Long.parseLong(value)).getName().toString()+" Albums");
			logger.info(value);

			model.addAttribute("albumsToShow", albumService.getAlbumsByGenre(Long.parseLong(value)));
			break;
		case("artist"):
			model.addAttribute("pagetitle", "Albums By "+value);
			allAlbums.forEach(x->{if(x.getArtist().toString().equals(value)) 
			filteredAlbums.add(x);
						});
			model.addAttribute("albumsToShow",filteredAlbums);
			break;
			
		}
		return "user/allalbums";
	}
	
	
}
