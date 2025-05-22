package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.Film;
import com.example.s27467_mp5.model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FilmRepositoryTest {

    @Autowired
    private FilmRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    private Film film;

    @BeforeEach
    void initData() {
        film = Film.builder()
                .title("Inception")
                .releaseYear(2010)
                .language("English")
                .genre(Set.of(Genre.ACTION, Genre.SCIENCE_FICTION))
                .build();
    }

    @Test
    void testSaveAndFetchFilm() {
        repository.save(film);
        entityManager.flush();

        assertNotNull(film.getId());
        Film found = repository.findByIdWithConnections(film.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Inception", found.getTitle());
        assertTrue(found.getGenre().contains(Genre.ACTION));
        assertTrue(found.getGenre().contains(Genre.SCIENCE_FICTION));
    }

    @Test
    void testFindAllReturnsListWithGenres() {
        repository.save(film);
        entityManager.flush();

        Iterable<Film> films = repository.findAll();
        assertTrue(films.iterator().hasNext());
        films.forEach(f -> assertFalse(f.getGenre().isEmpty()));
    }

    @Test
    void testSaveFilmWithoutGenreThrowsException() {
        film.setGenre(Set.of());
        assertThrows(ConstraintViolationException.class, () -> {
            repository.save(film);
            entityManager.flush();
        });
    }

    @Test
    void testSaveFilmWithNullGenreThrowsException() {
        film.setGenre(null);
        assertThrows(ConstraintViolationException.class, () -> {
            repository.save(film);
            entityManager.flush();
        });
    }

    @Test
    void testUpdateFilmGenres() {
        repository.save(film);
        entityManager.flush();

        Set<Genre> genres = new HashSet<>(film.getGenre());
        genres.add(Genre.THRILLER);
        film.setGenre(genres);
        repository.save(film);
        entityManager.flush();

        Film updated = repository.findByIdWithConnections(film.getId()).orElseThrow();
        assertTrue(updated.getGenre().contains(Genre.THRILLER));
    }

    @Test
    void testDeleteFilm() {
        repository.save(film);
        entityManager.flush();

        repository.delete(film);
        entityManager.flush();

        assertTrue(repository.findByIdWithConnections(film.getId()).isEmpty());
    }
}