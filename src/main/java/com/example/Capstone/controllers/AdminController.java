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
import com.example.Capstone.entities.Orders;
import com.example.Capstone.entities.User;
import com.example.Capstone.repositories.AlbumRepository;
import com.example.Capstone.services.AlbumService;
import com.example.Capstone.services.GenreService;
import com.example.Capstone.services.MusicService;
import com.example.Capstone.services.OrdersService;
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
	
	@Autowired
	OrdersService orderService;
	
	@RequestMapping(value= "/user/admin", method= RequestMethod.GET)
	public String AdminPage(Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
		return "/user/admin";
	}
	
	@RequestMapping(value= "/addGenre", method= RequestMethod.GET)
	public String addGenre(@RequestParam("addGenre") String addGenre, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
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
			model.addAttribute("genreStatusMessage", "Successfully Added Genre");
		}
		else model.addAttribute("genreStatusMessage", "Error: Genre Already Exists");
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/deleteGenre", method= RequestMethod.GET)
	public String deleteGenre(@RequestParam("deleteGenre") String deleteGenre, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		ArrayList<String> allGenre = new ArrayList<>();
		for(Album a : albums)
			allGenre.add(a.getGenre().getName());
		for(Music m : Musics)
			allGenre.add(m.getGenre().getName());
		if(!allGenre.contains(deleteGenre) && genreService.GetGenre(deleteGenre) != null)
		{
			genreService.DeleteMusic(genreService.GetGenre(deleteGenre));
			genres = genreService.GetAllGenre();
			model.addAttribute("genreStatusMessage", "Successfully Deleted Genre");
		}
		else model.addAttribute("genreStatusMessage", "Error: Genre Doesn't Exist");
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/editMusic", method= RequestMethod.GET)
	public String editMusic(@RequestParam("editSongName") String editSongName, @RequestParam("editSongID") Integer editSongID, @RequestParam("editTrackNumber") Integer editTrackNumber, @RequestParam("editSongPrice") Integer editSongPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		if(musicService.findMusic(editSongID) != null)
		{
			Music mEdit = musicService.findMusic(editSongID);
			mEdit.setName(editSongName);
			mEdit.setTrackNumber(editTrackNumber);
			mEdit.setPrice(BigDecimal.valueOf(editSongPrice));
			musicService.AddMusic(mEdit);
			model.addAttribute("songStatusMessage", "Successfully Edited Song");
		}
		else model.addAttribute("songStatusMessage", "Error Editing Song");
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/addMusic", method= RequestMethod.GET)
	public String addMusic(@RequestParam("addSongID") Integer addSongID, @RequestParam("addSongName") String addSongName, @RequestParam("addSongAlbum") String addSongAlbum, @RequestParam("addTrackNumber") Integer addTrackNumber, @RequestParam("addSongPrice") Integer addSongPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		ArrayList<Music> musicAL = (ArrayList<Music>) Musics;
		ArrayList<String> names = new ArrayList<>();
		ArrayList<Long> ids = new ArrayList<>();
		for(Music mus : musicAL)
		{
			names.add(mus.getName());
			ids.add(mus.getId());
		}
		Long id = new Long(addSongID);
		if(!names.contains(addSongName) && albumService.getAlbumsByName(addSongAlbum) != null && !ids.contains(id))
		{
			Album a = albumService.getAlbumsByName(addSongAlbum);
			//Music m = new Music(addSongName, a.getGenre(), a, addTrackNumber, BigDecimal.valueOf(addSongPrice));
			Music mus = new Music(id);
			//mus.setId(id);
			mus.setName(addSongName);
			mus.setGenre(a.getGenre());
			mus.setAlbum(a);
			mus.setTrackNumber(addTrackNumber);
			mus.setPrice(BigDecimal.valueOf(addSongPrice));
			musicService.AddMusic(mus);
			Musics = musicService.GetAllMusic();
			model.addAttribute("songStatusMessage", "Successfully Added Song");
		}
		else model.addAttribute("songStatusMessage", "Error Adding Song");
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/deleteSong", method= RequestMethod.GET)
	public String deleteSong(@RequestParam("deleteSong") Integer deleteSong, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		Long l = new Long(deleteSong);
		for(Music m : Musics)
		{
			if(m.getId().equals(l))
			{
				musicService.DeleteMusic(m);
				Musics = musicService.GetAllMusic();
				model.addAttribute("songStatusMessage", "Successfully Deleted Song");
			}
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/editUserActive", method= RequestMethod.GET)
	public String editActive(@RequestParam("editActive") Integer editActive, Model model)
	{
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		ArrayList<User> userAL = (ArrayList<User>) users;
		ArrayList<Integer> ids = new ArrayList<>();
		for(User u : userAL)
			ids.add(u.getId());
		if(ids.contains(editActive))
		{
			for(User user : userAL)
			{	
				if(user.getId() == editActive && !user.getUserName().equals("admin"))
				{
					user.setActive(!user.getActive());
					userService.save(user);
					model.addAttribute("userStatusMessage", "Successfully Edited User");
				}
				else model.addAttribute("userStatusMessage", "Error: You cannot set Admin to inactive");
			}
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/editAlbum", method= RequestMethod.GET)
	public String editAlbum(@RequestParam("editAlbumID") Integer editAlbumID, @RequestParam("editAlbumName") String editAlbumName, @RequestParam("editAlbumDate") Date editAlbumDate, @RequestParam("editAlbumPrice") Integer editAlbumPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		for(Album a : albums)
		{
			if(a.getId().equals(new Long(editAlbumID)))
			{
				a = albumService.getAlbumsByName(a.getName());
				a.setName(editAlbumName);
				a.setReleaseDate(editAlbumDate);
				a.setPrice(BigDecimal.valueOf(editAlbumPrice));	
				albumService.save(a);
				model.addAttribute("albumStatusMessage", "Successfully Edited Album");
			}
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/addAlbum", method= RequestMethod.GET)
	public String editAlbum(@RequestParam("addAlbumID") Integer addAlbumID, @RequestParam("addAlbumName") String addAlbumName, @RequestParam("addArtistName") String addArtistName, @RequestParam("addAlbumGenre") String addAlbumGenre, @RequestParam("addAlbumDate") Date addAlbumDate, @RequestParam("addAlbumPrice") Integer addAlbumPrice, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
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
			model.addAttribute("albumStatusMessage", "Successfully Added Album");
		}
		else model.addAttribute("albumStatusMessage", "Error Adding Album");
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	
	@RequestMapping(value= "/deleteAlbum", method= RequestMethod.GET)
	public String deleteAlbum(@RequestParam("deleteAlbum") Integer deleteAlbum, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		Long l = new Long(deleteAlbum);
		for(Album a : albums)
		{
			if(a.getId().equals(l) && !hasSongs(a))
			{
				
				albumService.delete(a);
				albums = albumService.getAlbums();
				model.addAttribute("albumStatusMessage", "Successfully Deleted Album");
			}
			else model.addAttribute("albumStatusMessage", "Error Deleting Album");
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
        return "/user/admin";
	}
	@RequestMapping(value= "/editOrderStatus", method= RequestMethod.GET)
	public String editStatus(@RequestParam("editOrderStatus") Integer editOrderStatus, @RequestParam("newStatus") String newStatus, Model model) {
		Iterable<Music> Musics = musicService.GetAllMusic();
		Iterable<Album> albums = albumService.getAlbums();
		Iterable<Genre> genres = genreService.GetAllGenre();
		Iterable<User> users = userService.findAllUsers();
		Iterable<Orders> orders = orderService.findAllOrders();
		
		for(Orders o : orders)
		{
			if(o.getId() == editOrderStatus)
			{
				o.setStatus(newStatus);
				orderService.save(o);
				model.addAttribute("orderStatusMessage", "Successfully Edited Status");
			}
			else model.addAttribute("orderStatusMessage", "Error: ID not found");
		}
		
        model.addAttribute("music", Musics);
        model.addAttribute("album", albums);
        model.addAttribute("genre", genres);
        model.addAttribute("user", users);
        model.addAttribute("order", orders);
		return "/user/admin";
	}
	
	public boolean hasSongs(Album a)
	{
		Iterable<Music> Musics = musicService.GetAllMusic();
		for(Music mus : Musics)
		{
			if(mus.getAlbum().equals(a))
				return true;
		}
		return false;
	}
}
