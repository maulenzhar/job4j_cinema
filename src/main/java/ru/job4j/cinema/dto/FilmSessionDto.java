package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;

import java.time.LocalDateTime;

public class FilmSessionDto implements Comparable<FilmSessionDto> {
    private int id;
    private FilmDto film;
    private Hall hall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int id, FilmDto film, Hall hall, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public FilmDto getFilm() {
        return film;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(FilmSessionDto o) {
        return this.startTime.compareTo(o.startTime);
    }

    public static class Builder {
        private int id;
        private FilmDto film;
        private Hall hall;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int price;

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildFilm(FilmDto filmDto) {
            this.film = filmDto;
            return this;
        }

        public Builder buildHall(Hall hall) {
            this.hall = hall;
            return this;
        }

        public Builder buildStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder buildEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder buildPrice(int price) {
            this.price = price;
            return this;
        }

        public FilmSessionDto build() {
            FilmSessionDto filmSessionDto = new FilmSessionDto();
            filmSessionDto.id = id;
            filmSessionDto.film = film;
            filmSessionDto.hall = hall;
            filmSessionDto.startTime = startTime;
            filmSessionDto.endTime = endTime;
            filmSessionDto.price = price;

            return filmSessionDto;
        }
    }
}
