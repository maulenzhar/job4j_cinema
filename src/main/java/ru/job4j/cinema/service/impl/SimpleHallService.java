package ru.job4j.cinema.service.impl;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;
import ru.job4j.cinema.service.HallService;

import java.util.Optional;

@Service
public class SimpleHallService implements HallService {

    private final HallRepository hallService;

    public SimpleHallService(HallRepository hallService) {
        this.hallService = hallService;
    }

    @Override
    public Optional<Hall> getHallById(int id) {
        return hallService.findById(id);
    }
}
