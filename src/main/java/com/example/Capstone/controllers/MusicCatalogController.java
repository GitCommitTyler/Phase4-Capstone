package com.example.Capstone.controllers;

import com.example.Capstone.entities.Music;
import com.example.Capstone.services.MusicService;

import java.util.ArrayList;

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
    public String search(@RequestParam("search") String searchterm, Model model) {
    	Iterable<Music> Musics = musicService.GetAllMusic();
    	ArrayList<Music> musicAL = new ArrayList<>();
    	for(Music m: Musics)
    	{
    		if(m.getName().toLowerCase().contains(searchterm.toLowerCase()))
    			musicAL.add(m);
    	}
        model.addAttribute("music", musicAL);
    	
    	return "music_catalog";
    }

}
