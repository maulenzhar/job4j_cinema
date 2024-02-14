package ru.job4j.cinema.repository.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.impl.Sql2oFilmRepository;
import ru.job4j.cinema.repository.impl.Sql2oFilmSessionRepository;
import org.junit.jupiter.api.BeforeAll;
import ru.job4j.cinema.repository.impl.Sql2oHallRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Sql2oFilmSessionRepositoryTest {
    private static Sql2oFilmSessionRepository sql2oFilmSessionRepository;
    private static Sql2oFilmRepository sql2oFilmRepository;
    private static Sql2oHallRepository sql2oHallRepository;
    private static Film film;
    private static Hall hall;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmSessionRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oFilmSessionRepository = new Sql2oFilmSessionRepository(sql2o);
        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
        sql2oHallRepository = new Sql2oHallRepository(sql2o);

        hall = new Hall("Hall", 10, 10, "");
        sql2oHallRepository.save(hall);

        film = new Film("Film", "Film description", 2024, 1, 18, 160, 1);
        sql2oFilmRepository.save(film);
    }

    @AfterEach
    public void clearFilmSession() {
        var filmSessions = sql2oFilmSessionRepository.findAll();
        for (var filmSession : filmSessions) {
            sql2oFilmSessionRepository.deleteById(filmSession.getId());
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var filmSession = sql2oFilmSessionRepository.save(new FilmSession(film.getId(), hall.getId(), LocalDateTime.now(), LocalDateTime.now(), 200));
        var savedFilmSession = sql2oFilmSessionRepository.findById(filmSession.getId()).get();
        assertThat(savedFilmSession).usingRecursiveComparison().isEqualTo(filmSession);
    }

    @Test
    public void whenSaveSeveralThenGetAll() {
        var filmSession1 = sql2oFilmSessionRepository.save(new FilmSession(film.getId(), hall.getId(), LocalDateTime.now(), LocalDateTime.now(), 200));
        var filmSession2 = sql2oFilmSessionRepository.save(new FilmSession(film.getId(), hall.getId(), LocalDateTime.now(), LocalDateTime.now(), 200));
        var filmSession3 = sql2oFilmSessionRepository.save(new FilmSession(film.getId(), hall.getId(), LocalDateTime.now(), LocalDateTime.now(), 200));
        var result = sql2oFilmSessionRepository.findAll();
        assertThat(result).isEqualTo(List.of(filmSession1, filmSession2, filmSession3));
    }

}
