package ru.job4j.cinema.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmSessionsRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionsRepository {
    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public FilmSession save(FilmSession film) {
        try (var connection = sql2o.open()) {
            var sql = """
                      INSERT INTO film_sessions (film_id, halls_id, start_time, end_time, price) VALUES 
                      (:filmId, :hallsId, :startTime, :endTime, :price);
                      """;
            var query = connection.createQuery(sql, true)
                    .addParameter("filmId", film.getFilmId())
                    .addParameter("hallsId", film.getHallsId())
                    .addParameter("startTime", film.getStartTime())
                    .addParameter("endTime", film.getEndTime())
                    .addParameter("price", film.getPrice());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            film.setId(generatedId);
            return film;
        }
    }

    @Override
    public Optional<FilmSession> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            var filmSession = query.setColumnMappings(FilmSession.COLUMN_MAPPING).executeAndFetchFirst(FilmSession.class);
            return Optional.ofNullable(filmSession);
        }
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(FilmSession.COLUMN_MAPPING)
                    .executeAndFetch(FilmSession.class);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            int result = query.executeUpdate().getResult();
            return result > 0;
        }
    }
}
