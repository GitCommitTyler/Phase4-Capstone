package com.example.Capstone.services;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Capstone.controllers.HomeController;
import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Genre;
import com.example.Capstone.repositories.AlbumRepository;

@Service
public class AlbumService {
	
	Logger logger = LoggerFactory.getLogger(AlbumService.class);
	@Autowired 
	private AlbumRepository albumRepository;
	
	public Iterable<Album> getAlbums(){
		return albumRepository.findAll();
	}
	
	public Album getAlbumById(long id) throws Exception {
		Optional<Album> album = albumRepository.findById((long) id);
		logger.info("found album: "+ album);
		if(!album.isPresent()) {
			throw new Exception();
		}
		else
			
			return album.get();
	}
	
	public Iterable<Album> getNewAlbums(LocalDate pastDate){
		return albumRepository.getAlbumsByReleaseDateGreaterThan(pastDate);
	}
	
	public Album getAlbum(String albumName)
	{
		Iterable<Album> allbums = getAlbums();
		for(Album a : allbums)
		{
			if(a.getName().equals(albumName))
				return a;
		}
		return null;
	}
	
	public Iterable<Album> getAlbumsByGenre(Long id){
		logger.info(albumRepository.getAlbumsByGenreId(id).toString());
		return albumRepository.getAlbumsByGenreId(id);
	}
	
	public void save(Album a)
	{
		albumRepository.save(a);
	}
}

