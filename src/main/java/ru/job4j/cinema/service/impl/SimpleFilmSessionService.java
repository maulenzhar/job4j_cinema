package ru.job4j.cinema.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.*;
import ru.job4j.cinema.repository.*;
import ru.job4j.cinema.service.FilmSessionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionsRepository filmSessionsRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;
    private final GenreRepository genreRepository;
    private final FileRepository fileRepository;

    public SimpleFilmSessionService(FilmSessionsRepository sql2oFilmSessionsRepository,
                                    FilmRepository sql2oFilmRepository,
                                    HallRepository sql2oHallRepository,
                                    GenreRepository sql2oGenreRepository,
                                    FileRepository sql2oFileRepository) {
        this.filmSessionsRepository = sql2oFilmSessionsRepository;
        this.filmRepository = sql2oFilmRepository;
        this.hallRepository = sql2oHallRepository;
        this.genreRepository = sql2oGenreRepository;
        this.fileRepository = sql2oFileRepository;
    }

    @Override
    public FilmSession save(FilmSession filmSession) {
        return null;
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        List<FilmSessionDto> result = new ArrayList<>();
        for (FilmSession filmSession : filmSessionsRepository.findAll()) {
            Hall hall = hallRepository.findById(filmSession.getHallsId()).orElse(new Hall());
            Film film = filmRepository.getFilmById(filmSession.getFilmId()).orElse(null);
            Genre genre = genreRepository.findById(film.getGenreId()).orElse(new Genre());

            FilmDto filmDto = new FilmDto.Builder()
                    .buildId(film.getId())
                    .buildName(film.getName())
                    .buildDescription(film.getDescription())
                    .buildYear(film.getYear())
                    .buildMinimalAge(film.getMinimalAge())
                    .buildDurationInMinutes(film.getDurationInMinutes())
                    .buildGenre(genre.getName())
                    .build();
            FilmSessionDto filmSessionDto = new FilmSessionDto.Builder()
                    .buildId(filmSession.getFilmId())
                    .buildHall(hall)
                    .buildFilm(filmDto)
                    .buildStartTime(filmSession.getStartTime())
                    .buildEndTime(filmSession.getEndTime())
                    .buildPrice(filmSession.getPrice())
                    .build();
            result.add(filmSessionDto);
        }
        return result;
    }

    @Override
    public Optional<FilmSessionDto> findById(int id) {
        FilmSession filmSession = filmSessionsRepository.findById(id).orElse(new FilmSession());
        Hall hall = hallRepository.findById(filmSession.getHallsId()).orElse(new Hall());
        Film film = filmRepository.getFilmById(filmSession.getFilmId()).orElse(null);
        Genre genre = genreRepository.findById(film.getGenreId()).orElse(new Genre());
        File file = fileRepository.findById(film.getFileId()).orElse(null);
        FilmDto filmDto = new FilmDto.Builder()
                .buildId(film.getId())
                .buildName(film.getName())
                .buildDescription(film.getDescription())
                .buildYear(film.getYear())
                .buildMinimalAge(film.getMinimalAge())
                .buildDurationInMinutes(film.getDurationInMinutes())
                .buildGenre(genre.getName())
                .buildFile(file)
                .build();
        FilmSessionDto filmSessionDto = new FilmSessionDto.Builder()
                .buildId(filmSession.getFilmId())
                .buildHall(hall)
                .buildFilm(filmDto)
                .buildStartTime(filmSession.getStartTime())
                .buildEndTime(filmSession.getEndTime())
                .buildPrice(filmSession.getPrice())
                .build();

        return Optional.ofNullable(filmSessionDto);
    }
}