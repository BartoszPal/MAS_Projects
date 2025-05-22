package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.Studio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudioRepository extends CrudRepository<Studio, Long> {
    List<Studio> findByName(String name);

    @Query("FROM Studio s WHERE s.name LIKE concat('%', :text, '%')")
    List<Studio> findByNameContaining(@Param("text") String text);

    @Query("FROM Studio s LEFT JOIN FETCH s.employs WHERE s.id = :id")
    Optional<Studio> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM Studio")
    List<Studio> findAllWithoutConnections();
}
