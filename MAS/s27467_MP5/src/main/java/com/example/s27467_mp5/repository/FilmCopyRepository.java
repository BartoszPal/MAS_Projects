package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.FilmCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmCopyRepository extends CrudRepository<FilmCopy, Long> {
    @Query("FROM FilmCopy f LEFT JOIN FETCH f.film WHERE f.id = :id")
    Optional<FilmCopy> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM FilmCopy ")
    List<FilmCopy> findAllWithoutConnections();
}
