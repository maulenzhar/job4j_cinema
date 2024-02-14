package ru.job4j.cinema.repository.impl;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oHallRepository implements HallRepository {

    private final Sql2o sql2o;

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Hall save(Hall hall) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO public.halls (name, row_count, place_count, description) 
                    VALUES (:name, :rowCount, :placeCount, :description);
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("name", hall.getName())
                    .addParameter("rowCount", hall.getRowCount())
                    .addParameter("placeCount", hall.getPlaceCount())
                    .addParameter("description", hall.getDescription());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            hall.setId(generatedId);
            return hall;
        }
    }

    @Override
    public Optional<Hall> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls WHERE id = :id");
            var file = query.addParameter("id", id)
                    .setColumnMappings(Hall.COLUMN_MAPPING)
                    .executeAndFetchFirst(Hall.class);
            return Optional.ofNullable(file);
        }
    }

    @Override
    public Collection<Hall> findAll() {
        return null;
    }

    @Override
    public Optional<Hall> getHallById(int id) {
        return Optional.empty();
    }
}
