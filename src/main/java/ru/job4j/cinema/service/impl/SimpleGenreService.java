package ru.job4j.cinema.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;
import ru.job4j.cinema.service.GenreService;

import java.util.Collection;

@Service
public class SimpleGenreService implements GenreService {

    private final GenreRepository genreRepository;

    public SimpleGenreService(GenreRepository sql2oGenreRepository) {
        this.genreRepository = sql2oGenreRepository;
    }

    @Override
    public Collection<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(int id) {
        return genreRepository.findById(id).orElse(new Genre());
    }
}
