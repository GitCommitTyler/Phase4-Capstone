package com.example.Capstone.repositories;

import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Music;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Music, Integer>{
	
	Iterable<Music> findByAlbum(Album album);
	Optional<Music> findById(Long id);
}