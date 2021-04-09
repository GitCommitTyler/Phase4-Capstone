package com.example.Capstone.repositories;

import com.example.Capstone.entities.Album;
import com.example.Capstone.entities.Music;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Music, Integer>{
	
	Iterable<Music> findByAlbum(Album album);
}