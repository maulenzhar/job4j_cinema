package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionsRepository {
    FilmSession save(FilmSession film);

    Optional<FilmSession> findById(int id);

    Collection<FilmSession> findAll();

    boolean deleteById(int id);
}
