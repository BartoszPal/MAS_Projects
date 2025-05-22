package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    @Query("FROM Rating r LEFT JOIN FETCH r.film LEFT JOIN FETCH r.client WHERE r.id = :id")
    Optional<Rating> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM Rating")
    List<Rating> findAllWithoutConnections();
}
