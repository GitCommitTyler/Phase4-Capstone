package com.example.Capstone.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value= "/addGenre", method= RequestMethod.GET)
	public String addGenre(@RequestParam("addGenre") String addGenre, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		
		ArrayList<Genre> genreAL = (ArrayList<Genre>) genres;
		ArrayList<String> names = new ArrayList<>();
		for(Genre gen: genreAL)
			names.add(gen.getName());
		if(!names.contains(addGenre))
		{
			Long size = new Long(genreAL.size());
			Genre g = new Genre(size+1, addGenre);
			genreService.AddGenre(g);
			genres = genreService.GetAllGenre();
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/deleteGenre", method= RequestMethod.GET)
	public String deleteGenre(@RequestParam("deleteGenre") String deleteGenre, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		ArrayList<String> allGenre = new ArrayList<>();
		for(Album a : albums)
			allGenre.add(a.getGenre().getName());
		for(Music m : Musics)
			allGenre.add(m.getGenre().getName());
		if(!allGenre.contains(deleteGenre) && genreService.GetGenre(deleteGenre) != null)
		{
			genreService.DeleteMusic(genreService.GetGenre(deleteGenre));
			genres = genreService.GetAllGenre();
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/editMusic", method= RequestMethod.GET)
	public String editMusic(@RequestParam("editSongName") String editSongName, @RequestParam("editSongID") Integer editSongID, @RequestParam("editTrackNumber") Integer editTrackNumber, @RequestParam("editSongPrice") Integer editSongPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		if(musicService.findMusic(editSongID) != null)
		{
			Music mEdit = musicService.findMusic(editSongID);
			mEdit.setName(editSongName);
			mEdit.setTrackNumber(editTrackNumber);
			mEdit.setPrice(BigDecimal.valueOf(editSongPrice));
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
}
