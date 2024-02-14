package ru.job4j.cinema.repository;

import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmRepository {
    Film save(Film film);

    Optional<Film> findByName(String name);

    Collection<Film> findAll();

    boolean deleteById(int id);

    Optional<Film> getFilmById(int id);
}
