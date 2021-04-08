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
import com.example.Capstone.entities.Genre;
import com.example.Capstone.entities.Music;
import com.example.Capstone.entities.User;
import com.example.Capstone.services.AlbumService;
import com.example.Capstone.services.GenreService;
import com.example.Capstone.services.MusicService;
import com.example.Capstone.services.UserService;


@Controller
public class AdminController {
	
	@Autowired
    MusicService musicService;
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value= "/user/admin", method= RequestMethod.GET)
	public String AdminPage(Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	
}
