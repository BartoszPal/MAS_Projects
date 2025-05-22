package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.DVD;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DVDRepository extends CrudRepository<DVD, Long> {
    @Query("FROM DVD d LEFT JOIN FETCH d.film WHERE d.id = :id")
    Optional<DVD> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM DVD")
    List<DVD> findAllWithoutConnections();
}
