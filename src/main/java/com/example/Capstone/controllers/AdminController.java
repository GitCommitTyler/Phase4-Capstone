package com.example.Capstone.controllers;

import java.math.BigDecimal;
import java.sql.Date;
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
import com.example.Capstone.repositories.AlbumRepository;
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
			musicService.AddMusic(mEdit);
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/addMusic", method= RequestMethod.GET)
	public String addMusic(@RequestParam("addSongName") String addSongName, @RequestParam("addSongAlbum") String addSongAlbum, @RequestParam("addTrackNumber") Integer addTrackNumber, @RequestParam("addSongPrice") Integer addSongPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		ArrayList<Music> musicAL = (ArrayList<Music>) Musics;
		ArrayList<String> names = new ArrayList<>();
		for(Music mus : musicAL)
			names.add(mus.getName());
		if(!names.contains(addSongName) && albumService.getAlbum(addSongAlbum) != null)
		{
			Album a = albumService.getAlbum(addSongAlbum);
			Long size = new Long(musicAL.size());
			//Music m = new Music(addSongName, a.getGenre(), a, addTrackNumber, BigDecimal.valueOf(addSongPrice));
			Music mus = new Music();
			mus.setName(addSongName);
			mus.setGenre(a.getGenre());
			mus.setAlbum(a);
			mus.setTrackNumber(addTrackNumber);
			mus.setPrice(BigDecimal.valueOf(addSongPrice));
			musicService.AddMusic(mus);
			Musics = musicService.GetAllMusic();
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/editUserActive", method= RequestMethod.GET)
	public String editActive(@RequestParam("editActive") Integer editActive, Model model)
	{
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		ArrayList<User> userAL = (ArrayList<User>) users;
		ArrayList<Integer> ids = new ArrayList<>();
		for(User u : userAL)
			ids.add(u.getId());
		if(ids.contains(editActive))
		{
			for(User user : userAL)
			{	
				if(user.getId() == editActive)
				{
					user.setActive(false);
					userService.saveUser(user);
				}
			}
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/editAlbum", method= RequestMethod.GET)
	public String editAlbum(@RequestParam("editAlbumID") Integer editAlbumID, @RequestParam("editAlbumName") String editAlbumName, @RequestParam("editAlbumDate") Date editAlbumDate, @RequestParam("editAlbumPrice") Integer editAlbumPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		
		for(Album a : albums)
		{
			if(a.getId().equals(new Long(editAlbumID)))
			{
				a = albumService.getAlbum(a.getName());
				a.setName(editAlbumName);
				a.setReleaseDate(editAlbumDate);
				a.setPrice(BigDecimal.valueOf(editAlbumPrice));	
				albumService.save(a);
			}
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/addAlbum", method= RequestMethod.GET)
	public String editAlbum(@RequestParam("addAlbumName") String addAlbumName, @RequestParam("addArtistName") String addArtistName, @RequestParam("addAlbumGenre") String addAlbumGenre, @RequestParam("addAlbumDate") Date addAlbumDate, @RequestParam("addAlbumPrice") Integer addAlbumPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		
		ArrayList<Album> albumAL = (ArrayList<Album>) albums;
		ArrayList<String> names = new ArrayList<>();
		for(Album a : albumAL)
			names.add(a.getName());
		if(!names.contains(addAlbumName))
		{
			Genre gen = genreService.GetGenre(addAlbumGenre);
			Album album = new Album(addAlbumName, gen, addArtistName, addAlbumDate, BigDecimal.valueOf(addAlbumPrice));
			albumService.save(album);
			albums = albumService.getAlbums();
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
		return "/user/admin";
	}
}
