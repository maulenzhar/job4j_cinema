package ru.job4j.cinema.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.service.FileService;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.GenreService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;
    private final FileService fileService;
    private final GenreService genreService;

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreService sql2oGenreService, FileService fileService) {
        this.filmRepository = sql2oFilmRepository;
        this.genreService = sql2oGenreService;
        this.fileService = fileService;
    }

    @Override
    public Film save(Film film, FileDto image) {
        saveNewFile(film, image);
        return filmRepository.save(film);
    }

    @Override
    public Collection<FilmDto> findAll() {
        List<FilmDto> result = new ArrayList<>();
        Collection<Film> films = filmRepository.findAll();
        for (Film film : films) {
            Genre genre = genreService.findById(film.getGenreId());
            FilmDto filmDto = new FilmDto.Builder()
                    .buildId(film.getId())
                    .buildName(film.getName())
                    .buildDescription(film.getDescription())
                    .buildYear(film.getYear())
                    .buildMinimalAge(film.getMinimalAge())
                    .buildDurationInMinutes(film.getDurationInMinutes())
                    .buildGenre(genre.getName())

                    .build();
            result.add(filmDto);
        }

        return result;
    }

    @Override
    public Optional<FilmDto> getFilmDtoById(int id) {
        Film film = filmRepository.getFilmById(id).orElse(new Film());
        Genre genre = genreService.findById(film.getGenreId());
        FilmDto filmDto = new FilmDto.Builder()
                .buildId(film.getId())
                .buildName(film.getName())
                .buildDescription(film.getDescription())
                .buildYear(film.getYear())
                .buildMinimalAge(film.getMinimalAge())
                .buildDurationInMinutes(film.getDurationInMinutes())
                .buildGenre(genre.getName())
                .build();
        return Optional.ofNullable(filmDto);
    }

    private void saveNewFile(Film film, FileDto image) {
        var file = fileService.save(image);
        film.setFileId(file.getId());
    }

}
