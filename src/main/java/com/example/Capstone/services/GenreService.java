package com.example.Capstone.services;

import com.example.Capstone.entities.Genre;
import com.example.Capstone.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository GenreRepository;

    public Iterable<Genre> GetAllGenre()
    {
        return GenreRepository.findAll();
    }


    public Optional<Genre> GetGenreById(Integer id)  {
        Optional<Genre> foundGenre = GenreRepository.findById(id);

        return (foundGenre);
    }
    public Genre GetGenre(Long id)  {
        Iterable<Genre> g = GetAllGenre();
        for(Genre gen : g)
        {
        	if(gen.getId() == id)
				return gen;
        }
        return null;
    }

    public Genre AddGenre(Genre Genre) {
        return GenreRepository.save(Genre);
    }

    public void DeleteMusic(Genre Genre) {
        GenreRepository.delete(Genre);
    }


}
