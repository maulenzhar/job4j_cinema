package ru.job4j.cinema.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oFilmRepository implements FilmRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sql2oFilmRepository.class.getName());
    private final Sql2o sql2o;

    public Sql2oFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Film save(Film film) {
        try (var connection = sql2o.open()) {
            var sql = """
                      INSERT INTO films(name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) 
                      VALUES (:name, :description, :year, :genreId, :minimalAge, :durationInMinutes, :fileId)
                      """;
            var query = connection.createQuery(sql, true)
                    .addParameter("name", film.getName())
                    .addParameter("description", film.getDescription())
                    .addParameter("year", film.getYear())
                    .addParameter("genreId", film.getGenreId())
                    .addParameter("minimalAge", film.getMinimalAge())
                    .addParameter("durationInMinutes", film.getDurationInMinutes())
                    .addParameter("fileId", film.getFileId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            film.setId(generatedId);
            return film;
        }
    }

    @Override
    public Optional<Film> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Collection<Film> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films");
            return query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetch(Film.class);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM filmsWHERE id = :id");
            query.addParameter("id", id);
            int result = query.executeUpdate().getResult();
            return result > 0;
        }
    }

    @Override
    public Optional<Film> getFilmById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films WHERE id = :id");
            var film = query.addParameter("id", id)
                    .setColumnMappings(Film.COLUMN_MAPPING)
                    .executeAndFetchFirst(Film.class);
            return Optional.ofNullable(film);
        }
    }
}
