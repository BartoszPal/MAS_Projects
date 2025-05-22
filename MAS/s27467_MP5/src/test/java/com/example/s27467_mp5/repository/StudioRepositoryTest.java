package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.Studio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudioRepositoryTest {

    @Autowired
    private StudioRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    Studio studio;

    @BeforeEach
    public void initData(){
        studio = Studio.builder().name("Studio 1").location("Anywhere").build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(repository);
    }

    @Test
    public void testFetchStudios() {
        Iterable<Studio> all = repository.findAll();
//        for(Studio studio : all) {
//            System.out.println(studio);
//        }
    }

    @Test
    public void testSaveStudio() {
        repository.save(studio);
        entityManager.flush();
        long count = repository.count();
        assertEquals(6, count);
    }

    @Test
    public void testSaveInvalidStudio() {
        assertThrows(ConstraintViolationException.class, () -> {
            studio.setName("S");
            repository.save(studio);
            entityManager.flush();
        });
    }

    @Test
    public void testFindByName() {
        List<Studio> studio = repository.findByName("Paramount Pictures");
        //System.out.println(studio);
        assertEquals(1, studio.size());
    }

    @Test
    public void testFindByNameContaining() {
        List<Studio> studio = repository.findByNameContaining(".");
        //System.out.println(studio);
        assertEquals(1, studio.size());
    }
}