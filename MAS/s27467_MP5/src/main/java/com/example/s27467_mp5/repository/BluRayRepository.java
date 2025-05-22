package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.BluRay;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BluRayRepository extends CrudRepository<BluRay, Long> {
    @Query("FROM BluRay b LEFT JOIN FETCH b.film WHERE b.id = :id")
    Optional<BluRay> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM BluRay")
    List<BluRay> findAllWithoutConnections();
}