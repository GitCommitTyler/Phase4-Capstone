package com.example.Capstone.repositories;

import com.example.Capstone.entities.Album;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer>{
	@Query(value="SELECT * FROM album WHERE release_date > ?1", nativeQuery=true)
	Iterable<Album> getAlbumsByReleaseDateGreaterThan(LocalDate Date);

	Optional<Album> findById(long id);
	
	
	Iterable<Album> getAlbumsByGenreId(long id);
	
	@Query("select distinct artist from Album")
	Iterable<String> getUniqueArtists();
	
}