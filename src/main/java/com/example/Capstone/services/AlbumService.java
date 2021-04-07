package com.example.Capstone.services;

import java.time.LocalDate;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Capstone.entities.Album;
import com.example.Capstone.repositories.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired 
	private AlbumRepository albumRepository;
	
	public Iterable<Album> getAlbums(){
		return albumRepository.findAll();
	}
	
	public Iterable<Album> getNewAlbums(LocalDate pastDate){
		return albumRepository.getAlbumsByReleaseDateGreaterThan(pastDate);
	}
}

