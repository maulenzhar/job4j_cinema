package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.service.FilmSessionService;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmSessionControllerTest {
    private FilmSessionService filmSessionService;
    private FilmSessionController filmSessionController;

    @BeforeEach
    public void initServices() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    @Test
    public void whenRequestGetAll() {
        var filmSession1 = new FilmSessionDto(1, new FilmDto("name", "desc", 1990, 18, 140, "", new File("", "")),
                new Hall("", 10, 10, ""),
                LocalDateTime.now(),
                LocalDateTime.now(),
                300
        );
        var filmSession2 = new FilmSessionDto(2, new FilmDto("name", "desc", 1990, 18, 140, "", new File("", "")),
                new Hall("", 10, 10, ""),
                LocalDateTime.now(),
                LocalDateTime.now(),
                300
        );
        var expectedVacancies = List.of(filmSession1, filmSession2);
        when(filmSessionService.findAll()).thenReturn(expectedVacancies);

        var model = new ConcurrentModel();
        var view = filmSessionController.getAll(model);
        var actualFilmSessions = model.getAttribute("filmSessions");

        assertThat(view).isEqualTo("film-sessions/list");
        assertThat(actualFilmSessions).isEqualTo(expectedVacancies);
    }

    @Test
    public void whenRequestGetById() {
        var expectedFilmSession = new FilmSessionDto(1, new FilmDto("name", "desc", 1990, 18, 140, "", new File("", "")),
                new Hall("", 10, 10, ""),
                LocalDateTime.now(),
                LocalDateTime.now(),
                300
        );
        var filmSessionArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        when(filmSessionService.findById(filmSessionArgumentCaptor.capture())).thenReturn(Optional.of(expectedFilmSession));

        var model = new ConcurrentModel();
        var view = filmSessionController.getById(model, expectedFilmSession.getId());
        var filmSessionVacancy = filmSessionArgumentCaptor.getValue();

        assertThat(view).isEqualTo("film-sessions/film-session");
        assertThat(filmSessionVacancy).isEqualTo(expectedFilmSession.getId());
    }

}