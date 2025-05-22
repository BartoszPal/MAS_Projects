package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query("FROM Client c LEFT JOIN FETCH c.ratings r LEFT JOIN FETCH c.employedBy WHERE c.id = :id")
    Optional<Client> findByIdWithConnections(@Param("id") Long id);

    @Query("FROM Client")
    List<Client> findAllWithoutConnections();

}
