package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> save(Ticket ticket);

    Collection<Ticket> findAll();
}
