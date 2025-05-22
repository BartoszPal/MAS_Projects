package com.example.s27467_mp5.repository;


import com.example.s27467_mp5.model.Client;
import com.example.s27467_mp5.model.Studio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AssociationTest {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Studio studio;
    private Client client;

    @BeforeEach
    void initData() {
        studio = Studio.builder().name("Studio 2").location("Anywhere").build();

        client = Client.builder()
                .name("Ala")
                .secondName(null)
                .surname("Nowakowska")
                .birth(LocalDate.of(1999,12,12))
                .build();
    }

    @Test
    void testRequiredDependencies() {
        assertNotNull(studioRepository);
        assertNotNull(clientRepository);
    }

    @Test
    void testSave(){
        studio.getEmploys().add(client);
        studioRepository.save(studio);
        client.setEmployedBy(studio);
        clientRepository.save(client);

        Optional<Studio> byId = studioRepository.findByIdWithConnections(studio.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getEmploys());
        assertEquals(1, byId.get().getEmploys().size());
    }
}
