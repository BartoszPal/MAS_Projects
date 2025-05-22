package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends CrudRepository<Film, Long> {
    @Query("FROM Film f LEFT JOIN FETCH f.filmCopies LEFT JOIN FETCH f.ratings WHERE f.id = :id")
    Optional<Film> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM Film")
    List<Film> findAllWithoutConnections();
}
