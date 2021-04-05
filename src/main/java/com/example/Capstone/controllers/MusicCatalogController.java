package com.example.Capstone.controllers;

import com.example.Capstone.entities.Genre;
import com.example.Capstone.entities.Music;
import com.example.Capstone.services.GenreService;
import com.example.Capstone.services.MusicService;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.ui.Model;


@Controller
public class MusicCatalogController {


    @Autowired
    MusicService musicService;
    
    @Autowired
    GenreService genreService;


    @RequestMapping(value = "/music_catalog", method = RequestMethod.GET)
    public String greeting(Model model) {

        Iterable<Music> Musics = musicService.GetAllMusic();

        model.addAttribute("music", musicService.GetAllMusic());
        return "music_catalog";
    }

    @RequestMapping(value = "/shopping_cart", method = RequestMethod.GET)
    public String cart(Model model) {
    	return "shopping_cart";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("search") String searchterm, @RequestParam("genre") String genre, Model model) {
    	Iterable<Music> Musics = musicService.GetAllMusic();
    	Iterable<Genre> Genre = genreService.GetAllGenre();
    	
    	ArrayList<Music> musicAL = new ArrayList<>();
    	for(Music m: Musics)
    	{
    		if(m.getName().toLowerCase().contains(searchterm.toLowerCase()) || m.getAlbum().getName().toLowerCase().contains(searchterm.toLowerCase()))
    		{
    			Genre gen = genreService.GetGenre(m.getId());
    			//System.out.println(gen.getName().toLowerCase());
    			//System.out.println(genre.toLowerCase());
    			if(genre.toLowerCase().equals("select..."))
    				musicAL.add(m);
    			if(gen.getName().toLowerCase().equals(genre.toLowerCase()))
        			musicAL.add(m);
    		}
    	}
        model.addAttribute("music", musicAL);
    	
    	return "music_catalog";
    }

}
