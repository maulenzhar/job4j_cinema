package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.TicketService;

import java.util.Collection;

@Controller
@RequestMapping("/film-sessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping
    public String getAll(Model model) {
        Collection<FilmSessionDto> all = filmSessionService.findAll();
        model.addAttribute("filmSessions", all);
        return "film-sessions/list";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        FilmSessionDto filmSessionDto = filmSessionService.findById(id).orElse(new FilmSessionDto());
        model.addAttribute("filmSession", filmSessionDto);
        return "film-sessions/film-session";
    }
}
