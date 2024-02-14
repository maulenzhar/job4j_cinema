package ru.job4j.cinema.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;
import ru.job4j.cinema.service.TicketService;

import java.util.Collection;
import java.util.List;

@Service
public class SimpleTicketService implements TicketService {
    private final TicketRepository ticketRepository;

    public SimpleTicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        for (Ticket currentTicket : findAll()) {
            if (ticket.getRowNumber() == currentTicket.getRowNumber()
                    && ticket.getPlaceNumber() == currentTicket.getPlaceNumber()) {
                return null;
            }
        }
        return ticketRepository.save(ticket);
    }

    @Override
    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
