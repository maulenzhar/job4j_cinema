package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.GenreService;

import java.util.Collection;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    private final GenreService genreService;

    public FilmController(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getAll(Model model) {
        Collection<FilmDto> all = filmService.findAll();
        model.addAttribute("films", all);
        return "films/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "films/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Film film, @RequestParam MultipartFile file, Model model) {
        try {
            filmService.save(film, new FileDto(file.getOriginalFilename(), file.getBytes()));
            return "redirect:/films";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }
}
