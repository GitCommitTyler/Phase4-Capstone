package com.example.Capstone.controllers;

import com.example.Capstone.entities.Genre;
import com.example.Capstone.entities.Music;
import com.example.Capstone.entities.User;
import com.example.Capstone.services.GenreService;
import com.example.Capstone.services.MusicService;
import com.example.Capstone.services.OrdersService;
import com.example.Capstone.services.UserService;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;


@Controller
public class MusicCatalogController {
	@Autowired
	UserService userService;

    @Autowired
    MusicService musicService;
    
    @Autowired
    GenreService genreService;
    
    
    
//    @GetMapping(value="/user/index")
//    public ModelAndView userHome() {
//    	ModelAndView modelAndView = new ModelAndView();
//    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    	User user = userService.findUserByUserName(auth.getName());
//    	modelAndView.setViewName("user/index");
//    	return modelAndView;
//    }

    @RequestMapping(value = "/user/search_page", method = RequestMethod.GET)
    public String searchHome(Model model) {

        Iterable<Music> Musics = musicService.GetAllMusic();
        Iterable<Genre> genres = genreService.GetAllGenre();
        model.addAttribute("music", musicService.GetAllMusic());
        model.addAttribute("genre", genres);
        return "user/search";
    }
    
    @RequestMapping(value = "/user/music_catalog", method = RequestMethod.GET)
    public String greeting(Model model) {

        
        return "user/music_catalog";
    }

    @RequestMapping(value = "/user/shopping_cart", method = RequestMethod.GET)
    public String cart(Model model) {
    	return "user/shopping_cart";
    }
    
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public String search(@RequestParam("search") String searchterm, @RequestParam("genre") String genre, @RequestParam("rangemin") Integer min, @RequestParam("rangemax") Integer max, Model model) {
    	Iterable<Music> Musics = musicService.GetAllMusic();
    	Iterable<Genre> Genre = genreService.GetAllGenre();
    	
    	ArrayList<Music> musicAL = new ArrayList<>();
    	for(Music m: Musics)
    	{
    		if(m.getName().toLowerCase().contains(searchterm.toLowerCase()) || m.getAlbum().getName().toLowerCase().contains(searchterm.toLowerCase()))
    		{
    			Genre gen = m.getAlbum().getGenre();
    			//System.out.println(gen.getName().toLowerCase());
    			//System.out.println(genre.toLowerCase());
    			if(gen.getName().toLowerCase().equals(genre.toLowerCase()))
    			{
    				System.out.println(min + "  " + max + "  " + m.getPrice().intValue());
    				if(min == 0 && max == 0)
    					musicAL.add(m);
    				else if(m.getPrice().intValue() >= min && m.getPrice().intValue() < max)
    					musicAL.add(m);
    			}
    		}
    	}
        model.addAttribute("music", musicAL);
        model.addAttribute("genre", Genre);
    	return "user/search";
    }

	
	//@GetMapping(value="/user/music/{trackName}")
	//public Music findTrack(@PathVariable(value="trackName") String trackName) {
	//	return musicService.GetMusicByTrackName(trackName);
	//}

}
