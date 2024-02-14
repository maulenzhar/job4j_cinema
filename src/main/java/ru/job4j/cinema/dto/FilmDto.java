package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.File;

public class FilmDto {
    private int id;
    private String name;
    private String description;
    private int year;
    private int minimalAge;
    private int durationInMinutes;
    private String genre;
    private File file;

    public FilmDto() {
    }

    public FilmDto(String name, String description, int year, int minimalAge, int durationInMinutes, String genre, File file) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.minimalAge = minimalAge;
        this.durationInMinutes = durationInMinutes;
        this.genre = genre;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public File getFile() {
        return file;
    }

    public static class Builder {
        private int id;
        private String name;
        private String description;
        private int year;
        private int minimalAge;
        private int durationInMinutes;
        private String genre;
        private File file;

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder buildYear(int year) {
            this.year = year;
            return this;
        }

        public Builder buildMinimalAge(int minimalAge) {
            this.minimalAge = minimalAge;
            return this;
        }

        public Builder buildDurationInMinutes(int durationInMinutes) {
            this.durationInMinutes = durationInMinutes;
            return this;
        }

        public Builder buildGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder buildFile(File file) {
            this.file = file;
            return this;
        }

        public FilmDto build() {
            FilmDto filmDto = new FilmDto();
            filmDto.id = id;
            filmDto.name = name;
            filmDto.description = description;
            filmDto.year = year;
            filmDto.minimalAge = minimalAge;
            filmDto.durationInMinutes = durationInMinutes;
            filmDto.genre = genre;
            filmDto.file = file;
            return filmDto;
        }
    }
}
