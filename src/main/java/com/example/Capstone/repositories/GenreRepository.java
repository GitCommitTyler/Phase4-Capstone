package com.example.Capstone.repositories;

import com.example.Capstone.entities.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer>{
}