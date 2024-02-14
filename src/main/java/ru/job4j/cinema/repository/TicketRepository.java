package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;

public interface TicketRepository {
    Ticket save(Ticket ticket);

    Collection<Ticket> findAll();
}
